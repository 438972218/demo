package com.xdcplus.cabinetel.basic.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.xdcplus.cabinetel.basic.mapper.eb1.HistoryAveEb1Mapper;
import com.xdcplus.cabinetel.basic.mapper.eb2.HistoryAveEb2Mapper;
import com.xdcplus.cabinetel.basic.service.HistoryAveService;
import com.xdcplus.cabinetel.common.service.impl.BaseServiceImpl;
import com.xdcplus.model.dto.cabinetel.CabinetelDTO;
import com.xdcplus.model.dto.cabinetel.HistoryDTO;
import com.xdcplus.model.dto.cabinetel.QueryDTO;
import com.xdcplus.resource.basic.service.CabinetService;
import com.xdcplus.utils.exception.CustomException;
import com.xdcplus.utils.string.StringUtil;
import com.xdcplus.utils.time.DateUtils;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Martin.Ji
 * @since 2018/1/3
 */
@Service(version = "1.0.0")
public class HistoryAveServiceImpl extends BaseServiceImpl implements HistoryAveService {

    @Autowired
    HistoryAveEb2Mapper historyAveEb2Mapper;
    @Autowired
    HistoryAveEb1Mapper historyAveEb1Mapper;

    @Reference(version = "1.0.0")
    CabinetService cabinetService;

    @Override
    public void insertOfCabinetelAveDetail(QueryDTO queryDTO) throws CustomException {
        if (queryDTO == null || queryDTO.getDatacenterId() == null) {
            throw new CustomException("queryDTO, datacenterId not found");
        }
        if (queryDTO.getToTable() == null || "".equals(queryDTO.getToTable())) {
            queryDTO.setToTable("history_ave_" + DateUtils.getyyyyMM(queryDTO.getFrom()));
        }
        queryDTO.setTable("history");
        List<CabinetelDTO> cabinetelDTOS;
        if (StringUtil.isEmpty(queryDTO.getCabinetNumber())) {
            cabinetelDTOS = cabinetService.selectCabinetelDTOsOnlySByQueryDTOAndDatacenterId(queryDTO);
        } else {
            cabinetelDTOS = cabinetService.selectCabinetelDTOsByQueryDTO(queryDTO);
        }

        ExecutorService pool = Executors.newCachedThreadPool();
        long a = cabinetelDTOS.stream()
                .map(cabinetelDTO -> CompletableFuture.supplyAsync(() -> insertData(queryDTO, cabinetelDTO), pool).join())
                .count();
        pool.shutdown();
    }

//    @DS("eb2")
    private Integer insertData(QueryDTO queryDTO, CabinetelDTO cabinetelCfgDTO) {
        if (cabinetelCfgDTO.getHistoryDTOS() != null && cabinetelCfgDTO.getHistoryDTOS().size() % 2 != 0) {
            return null;
        }
        queryDTO.setCabinetId(cabinetelCfgDTO.getCabinetId());
        List<Integer> itemids = new ArrayList<>();
        for (HistoryDTO historyDTO : cabinetelCfgDTO.getHistoryDTOS()) {
            itemids.add(historyDTO.getItemId());
        }
        queryDTO.setItemIds(itemids);

        if (queryDTO.getFrom() == null) {
            queryDTO.setFrom(DateUtils.getLastMinuteFirstSecondTimestamp());
        }
        if (queryDTO.getTo() == null) {
            queryDTO.setTo(DateUtils.getLastMinuteLastSecondTimestamp());
        }
        try {
            if (queryDTO.getDatacenterId().equals(CON_EB2_ID)) {
                return historyAveEb1Mapper.insertOfCabinetelAveDetail(queryDTO);
            }else{
                return historyAveEb2Mapper.insertOfCabinetelAveDetail(queryDTO);
            }
        } catch (Exception ignored) {
            return null;
        } finally {
        }
    }


}
