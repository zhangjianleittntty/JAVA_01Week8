package com.java01.week8.databaseweek.question2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Program: databaseweek
 * @ClassName: OrderInfo
 * @Author: zhangjl
 * @Date: 2021-03-11 12:02
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInfo extends BaseInfoDO {
    private Long id;
    private Long orderId;
    private Long skuId;
    private String orderPin;
    private String orderName;
    private BigDecimal salePrice;
    private Date created;
    private Date modified;

    @Override
    public String getPhysicDataBaseName() {
        return String.valueOf((orderId % maxDataBaseCount) + 1);
    }

    @Override
    public String getPhysicTableName() {
        Long tableSizeStep = (orderId % maxDataBaseCount) * maxTableNameCount;
        return String.valueOf(orderId % maxTableNameCount + tableSizeStep + 1);
    }

    public static void main(String[] args) {
        OrderInfo orderInfo = OrderInfo.builder().orderId(989838383339L).build();
        System.out.println(orderInfo.getPhysicDataBaseName());
        System.out.println(orderInfo.getPhysicTableName());
    }
}
