//package com.java01.week8.databaseweek.question2.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
//import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * @Program: databaseweek
// * @ClassName: DataSourceConfig
// * @Author: zhangjl
// * @Date: 2021-03-13 17:31
// * @Description:
// */
//@Configuration
//@Slf4j
//@MapperScan(basePackages = "com.java01.week8.databaseweek.question2.mapper",sqlSessionFactoryRef = "sessionFactory")
//public class DataSourceConfig {
//
//    /**
//     * 配置数据源0，数据源的名称最好要有一定的规则，方便配置分库的计算规则
//     *
//     * @return
//     */
//    @Bean(name = "dataSource1")
//    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.datasource1")
//    public DataSource dataSource1() {
//        return DataSourceBuilder.create().build();
//    }
//
//    /**
//     * 配置数据源1，数据源的名称最好要有一定的规则，方便配置分库的计算规则
//     *
//     * @return
//     */
//    @Bean(name = "dataSource2")
//    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.datasource2")
//    public DataSource dataSource2() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    public SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setTypeAliasesPackage("com.java01.week8.databaseweek.question2.model");
//
//        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//        configuration.setMapUnderscoreToCamelCase(true);
//        sessionFactory.setConfiguration(configuration);
//
//        return sessionFactory.getObject();
//    }
//
//    @Bean
//    public DataSource dataSource() throws SQLException {
//
//        // 配置分片规则
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//
//        // 配置真实数据源
//        Map<String, DataSource> dataSourceMap = new HashMap<>();
//        dataSourceMap.put("datasource1", dataSource1());
//        dataSourceMap.put("datasource2", dataSource2());
//
//        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("orderinfo", "orderweek_${0..1}.orderinfo_${0..1}");
//        // 配置分库 + 分表策略
//        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("orderId", "orderweek_${orderId % 2}"));
//        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("orderId", "orderinfo_${orderId % 16}"));
//
//        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);
//
//        // 获取数据源对象
//        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());
//        return dataSource;
//    }
//
//    @Bean
//    public DataSourceTransactionManager shardTransactionManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//}
