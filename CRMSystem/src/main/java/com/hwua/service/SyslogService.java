package com.hwua.service;

import com.hwua.pojo.Syslog;

import java.util.List;

public interface SyslogService {
    public List<Syslog> queryAllSyslog()throws Exception;
    public int addSyslog(Syslog syslog)throws Exception;
}
