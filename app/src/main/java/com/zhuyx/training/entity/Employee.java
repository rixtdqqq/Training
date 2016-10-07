package com.zhuyx.training.entity;

/**
 * 员工个人信息实体
 * Created by zhuyingxin on 2016/9/26.
 * email : rixtdqqq_2015@163.com
 */

public class Employee {
    private String id;
    private String name;
    private String age;
    /**
     * 身高
     */
    private String stature;
    /**
     * 体重
     */
    private String weight;
    /**
     * 血型
     */
    private String bloodType;
    /**
     * 联系方式:q为qq,w为微信,@为邮件,tel为电话
     */
    private String contactWay;
    /**
     * 性别
     */
    private String gender;
    /**
     * 家庭地址
     */
    private String homeAddress;
    /**
     * 工作地址
     */
    private String workAddress;
    /**
     * 个人简介
     */
    private String personalBrief;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getStature() {
        return stature;
    }

    public String getWeight() {
        return weight;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getContactWay() {
        return contactWay;
    }

    public String getGender() {
        return gender;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public String getPersonalBrief() {
        return personalBrief;
    }

    public Employee(String id) {
        this.id = id;
    }

    public Employee(String id, String name, String age, String stature, String weight,
                    String bloodType, String contactWay, String gender, String homeAddress,
                    String workAddress, String personalBrief) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.stature = stature;
        this.weight = weight;
        this.bloodType = bloodType;
        this.contactWay = contactWay;
        this.gender = gender;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
        this.personalBrief = personalBrief;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setStature(String stature) {
        this.stature = stature;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public void setPersonalBrief(String personalBrief) {
        this.personalBrief = personalBrief;
    }
}
