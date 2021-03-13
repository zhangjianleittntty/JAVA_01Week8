package com.java01.week8.databaseweek;

//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.java01.week8.databaseweek.question2.mapper.OrderInfoMapper;
import com.java01.week8.databaseweek.question2.model.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.math.BigDecimal;

@SpringBootApplication
@Slf4j
//@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
@MapperScan("com.java01.week8.databaseweek.question2.mapper")
public class DatabaseweekApplication implements CommandLineRunner {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private OrderInfoMapper orderInfoMapper;

	public static void main(String[] args) {
		SpringApplication.run(DatabaseweekApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("DatabaseweekApplicationcc:{}",dataSource.toString());
		OrderInfo orderInfo = OrderInfo.builder().orderId(3344556l).skuId(3344556l).orderName("ccccc")
				.salePrice(new BigDecimal("345.3")).build();
		int count = orderInfoMapper.save(orderInfo);
		log.info("Save {}, dataBase:{}, tableName:{} OrderInfo: {}", count,orderInfo.getPhysicDataBaseName(),orderInfo.getPhysicTableName() ,orderInfo);
	}

}
