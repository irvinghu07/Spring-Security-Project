package com.imooc.domain;

import io.swagger.annotations.ApiModelProperty;

public class QueryCondition {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户年龄")
    private String age;

    @ApiModelProperty(value = "用户年龄区间")
    private String ageTo;

    @ApiModelProperty(value = "xxx")
    private String xxx;

    public QueryCondition(String username, String age, String ageTo, String xxx) {
        this.username = username;
        this.age = age;
        this.ageTo = ageTo;
        this.xxx = xxx;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(String ageTo) {
        this.ageTo = ageTo;
    }

    public String getXxx() {
        return xxx;
    }

    public void setXxx(String xxx) {
        this.xxx = xxx;
    }
}
