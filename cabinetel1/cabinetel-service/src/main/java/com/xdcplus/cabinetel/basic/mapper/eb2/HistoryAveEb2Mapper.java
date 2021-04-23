package com.xdcplus.cabinetel.basic.mapper.eb2;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.xdcplus.model.dto.cabinetel.CabinetelDTO;
import com.xdcplus.model.dto.cabinetel.HistoryMoreDTO;
import com.xdcplus.model.dto.cabinetel.QueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@DS("eb2")
public interface HistoryAveEb2Mapper {

    Integer insertOfCabinetelAveDetail(QueryDTO queryDTO);

}
