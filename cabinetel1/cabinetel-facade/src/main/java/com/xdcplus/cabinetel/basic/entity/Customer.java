package com.xdcplus.cabinetel.basic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author fish
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    private String name;

    private String fullName;

    private String statusName;

    private String level;

    private String type;

    private String trade;

    private String region;

    private String city;

    private String postalCode;

    private String fax;

    private String sourceFrom;

    private String address;

    private String officePhone;

    private String officialSite;

    private String billTo;

    private String shipTo;

    private String remark;

    private Integer createById;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    private Integer salesId;

    private Integer typeId;

    private Integer tradeId;

    private Integer regionId;

    private Integer cityId;


}
