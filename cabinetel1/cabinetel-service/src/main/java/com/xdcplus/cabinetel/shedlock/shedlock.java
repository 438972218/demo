package com.xdcplus.cabinetel.shedlock;

import com.xdcplus.cabinetel.basic.service.HistoryAveService;
import com.xdcplus.model.dto.cabinetel.QueryDTO;
import com.xdcplus.utils.exception.CustomException;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : Fish Fei
 */
@Component
@EnableScheduling
public class shedlock {
    //45秒
    protected static final int FOURTEEN_MIN = 45 * 1000;

    @Autowired
    HistoryAveService historyAveService;

    @Scheduled(cron = "0 */1 * * * ?")
    @SchedulerLock(name = "insertave", lockAtMostFor = FOURTEEN_MIN, lockAtLeastFor = FOURTEEN_MIN)
    public void insert(){
        QueryDTO queryDTO=new QueryDTO();
        try {
            queryDTO.setFrom(1618378100l);
            queryDTO.setTo(1618378203l);
            queryDTO.setDatacenterId(22);
            historyAveService.insertOfCabinetelAveDetail(queryDTO);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }
}
