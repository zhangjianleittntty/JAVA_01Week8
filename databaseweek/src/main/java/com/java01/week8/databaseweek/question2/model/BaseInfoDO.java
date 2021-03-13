package com.java01.week8.databaseweek.question2.model;

import lombok.Data;

/**
 * @Program: databaseweek
 * @ClassName: BaseInfoDO
 * @Author: zhangjl
 * @Date: 2021-03-11 15:40
 * @Description:
 */
public class BaseInfoDO {

    protected Integer maxDataBaseCount = 2;
    protected Integer maxTableNameCount = 16;

    private String physicDataBaseName;
    private String physicTableName;

    public String getPhysicDataBaseName() {
        return physicDataBaseName;
    }

    public void setPhysicDataBaseName(String physicDataBaseName) {
        this.physicDataBaseName = physicDataBaseName;
    }

    public String getPhysicTableName() {
        return physicTableName;
    }

    public void setPhysicTableName(String physicTableName) {
        this.physicTableName = physicTableName;
    }
}
