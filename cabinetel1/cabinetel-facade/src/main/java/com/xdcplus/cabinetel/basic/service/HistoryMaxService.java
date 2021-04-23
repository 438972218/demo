package com.xdcplus.cabinetel.basic.service;

import com.xdcplus.model.dto.cabinetel.QueryDTO;
import com.xdcplus.utils.exception.CustomException;

/**
 * @author Martin.Ji
 * @since 2019/1/25
 */
public interface HistoryMaxService {

    void insertOfCabinetelMaxDetail(QueryDTO queryDTO) throws CustomException;

}
