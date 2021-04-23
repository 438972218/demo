package com.xdcplus.cabinetel.basic.controller;


import com.xdcplus.cabinetel.basic.service.HistoryAveService;
import com.xdcplus.model.dto.cabinetel.QueryDTO;
import com.xdcplus.utils.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fish
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/historyave")
public class HistoryAveController {

    @Autowired
    HistoryAveService historyAveService;

    @PostMapping("/")
    public void insert(@RequestBody QueryDTO queryDTO){
        try {
//            queryDTO.setFrom(1618378100l);
//            queryDTO.setTo(1618378203l);
//            queryDTO.setDatacenterId(22);
            historyAveService.insertOfCabinetelAveDetail(queryDTO);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }

}
