package com.java01.week8.databaseweek.question2.mapper;

import com.java01.week8.databaseweek.question2.model.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
//import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
//import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Program: databaseweek
 * @ClassName: OrderInfoMapper
 * @Author: zhangjl
 * @Date: 2021-03-11 12:05
 * @Description:
 */
@Mapper
public interface OrderInfoMapper {
    OrderInfo findById(@Param("id") Long id);

    // 自己实现分库分表，无分布式事务
    @Transactional
    @Insert("insert into orderweek_${physicDataBaseName}.orderinfo_${physicTableName}(order_id,sku_id,order_pin,order_name,sale_price,created,modified)" +
            " values(#{orderId},#{skuId},#{orderPin},#{orderName},#{salePrice},now(),now())")
    @Options(useGeneratedKeys = true)        // 强制使用自增主键
    int save(OrderInfo orderInfo);

    /**
     * ShardingTransaction实现分布式事务
     * @param orderInfo
     * @return
     */
//    @Transactional
//    @ShardingTransactionType(TransactionType.XA)
//    @Insert("insert into orderinfo(order_id,sku_id,order_pin,order_name,sale_price,created,modified)" +
//            " values(#{orderId},#{skuId},#{orderPin},#{orderName},#{salePrice},now(),now())")
//    @Options(useGeneratedKeys = true)        // 强制使用自增主键
//    int save(OrderInfo orderInfo);
}
