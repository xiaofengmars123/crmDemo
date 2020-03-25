package com.hwua.pojo;

import lombok.Data;

import java.util.Date;


@Data
public class Syslog {
    /**
    * 主键
    */
    private String id;
    /**
    * 访问时间
    */
    private Date visitTime;
    /**
    * 操作者用户
    */
    private String username;
    /**
    * 访问ip
    */
    private String ip;
    /**
    * 访问url
    */
    private String url;
    /**
    * 执行时长
    */
    private Integer executionTime;
    /**
    * 访问方法
    */
    private String method;

}