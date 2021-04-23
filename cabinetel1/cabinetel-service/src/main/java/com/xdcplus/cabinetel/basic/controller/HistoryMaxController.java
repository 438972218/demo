package com.xdcplus.cabinetel.basic.controller;


import com.xdcplus.cabinetel.basic.service.HistoryMaxService;
import com.xdcplus.model.dto.cabinetel.QueryDTO;
import com.xdcplus.utils.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fish
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/historymax")
public class HistoryMaxController {

    @Autowired
    HistoryMaxService historyMaxService;

    @PostMapping("/")
    public void insert(@RequestBody QueryDTO queryDTO){
        try {
//            queryDTO.setFrom(1618378100l);
//            queryDTO.setTo(1618378203l);
//            queryDTO.setDatacenterId(22);
            historyMaxService.insertOfCabinetelMaxDetail(queryDTO);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }

}
