package com.hwua.mapper;

import com.hwua.pojo.Syslog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Syslog)表数据库访问层
 *
 * @author 马涛
 * @since 2020-03-05 16:49:07
 */
@Mapper
public interface SyslogMapper {


    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<Syslog> queryAll();

    /**
     * 新增数据
     *
     * @param syslog 实例对象
     * @return 影响行数
     */
    int insert(Syslog syslog);


}