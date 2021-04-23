package com.xdcplus.cabinetel.basic.service;

import com.xdcplus.model.dto.cabinetel.QueryDTO;
import com.xdcplus.utils.exception.CustomException;

/**
 * @author Martin.Ji
 * @since 2018/1/3
 */
public interface HistoryAveService {

    void insertOfCabinetelAveDetail(QueryDTO queryDTO) throws CustomException;

}
