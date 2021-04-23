package com.xdcplus.cabinetel.basic.mapper.eb1;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.xdcplus.model.dto.cabinetel.QueryDTO;

@DS("eb1")
public interface HistoryAveEb1Mapper {

    Integer insertOfCabinetelAveDetail(QueryDTO queryDTO);

}
