package com.hwua.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwua.pojo.Permission;
import com.hwua.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @ResponseBody
    @RequestMapping("permission-list.do/{pageNo}/{pageSize}")
    public ModelAndView findAllPermissions(@PathVariable("pageNo") String pageNo, @PathVariable("pageSize") String pageSize)throws Exception{
        ModelAndView mv=new ModelAndView();
        PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        List<Permission> list=permissionService.findAllPermissions();
        PageInfo pageInfo = new PageInfo<>(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }
    @RequestMapping("/permission/save.do")
    public String savePermission(Permission permission)throws Exception{

        Integer rs=  permissionService.addPermission(permission);
        if(rs==1){
            return "redirect:/permission-list.do/1/3";
        }else {
            return null;
        }
    }
    @RequestMapping("/permission/findById.do/{pid}/{pageNo}/{pageSize}")
    public ModelAndView findPermissionByPid(@PathVariable("pid") String id,@PathVariable("pageNo") String pageNo, @PathVariable("pageSize") String pageSize)throws Exception{
        ModelAndView mv=new ModelAndView();
        PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        List<Permission> list=permissionService.findPermissionById(id);
        System.out.println(list);
        PageInfo pageInfo = new PageInfo<>(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-show");
        return mv;
    }
}
