package com.xdcplus.cabinetel.basic.mapper.eb1;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.xdcplus.model.dto.cabinetel.QueryDTO;

/**
 * @author Martin.Ji
 * @since 2019/3/23
 */
@DS("eb1")
public interface HistoryMaxEb1Mapper {

    Integer insertOfCabinetelMaxDetail(QueryDTO queryDTO);

}
