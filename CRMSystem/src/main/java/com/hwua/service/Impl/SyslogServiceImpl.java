package com.hwua.service.Impl;

import com.hwua.mapper.SyslogMapper;
import com.hwua.pojo.Syslog;
import com.hwua.service.SyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SyslogServiceImpl implements SyslogService {
    @Autowired
    private SyslogMapper syslogMapper;
    @Override
    public List<Syslog> queryAllSyslog() throws Exception {
        return syslogMapper.queryAllSyslog();
    }

    @Override
    public int addSyslog(Syslog syslog) throws Exception {
        return syslogMapper.addSyslog(syslog);
    }
}
