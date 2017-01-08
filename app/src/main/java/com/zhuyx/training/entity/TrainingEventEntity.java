package com.zhuyx.training.entity;

/**
 * Created by zhuyingxin on 2016/11/10.
 * email : rixtdqqq_2015@163.com
 */

public class TrainingEventEntity {
    private String name;
    private String id;

    public TrainingEventEntity(String name) {
        this.name = name;
    }

    public TrainingEventEntity(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
