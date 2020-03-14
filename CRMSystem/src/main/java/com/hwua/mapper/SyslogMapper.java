package com.hwua.mapper;

import com.hwua.pojo.Syslog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface SyslogMapper {
    public List<Syslog> queryAllSyslog()throws Exception;
    public int addSyslog(Syslog syslog)throws Exception;
}
