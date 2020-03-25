package com.hwua.service;

import com.hwua.pojo.Syslog;

import java.util.List;

/**
 * (Syslog)表服务接口
 *
 * @author 马涛
 * @since 2020-03-05 16:49:07
 */
public interface SyslogService {



    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<Syslog> queryAll();

    /**
     * 新增数据
     *
     * @param syslog 实例对象
     * @return 实例对象
     */
    Syslog insert(Syslog syslog);




}