package com.hwua.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwua.pojo.Syslog;
import com.hwua.service.SyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SyslogController {
    @Autowired
    private SyslogService syslogService;
    @ResponseBody
    @RequestMapping("/syslog-list.do/{pageNo}/{pageSize}")
    public ModelAndView findAllSyslog(@PathVariable("pageNo") String pageNo, @PathVariable("pageSize") String pageSize)throws Exception{
        ModelAndView mv=new ModelAndView();
        PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        List<Syslog> list=syslogService.queryAllSyslog();
        PageInfo pageInfo = new PageInfo<>(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }
}
