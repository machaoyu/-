package com.hwua.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwua.pojo.Permission;
import com.hwua.pojo.Role;
import com.hwua.pojo.Users;
import com.hwua.service.PermissionService;
import com.hwua.service.RoleService;
import com.hwua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @ResponseBody
    @RequestMapping("user-role-add.do/{uid}")
    public ModelAndView showAllRoles(@PathVariable("uid") String uid)throws Exception{
        ModelAndView mv=new ModelAndView();
        Users user=userService.findUserById(uid);
        List<Role> list=roleService.findAllRoles();
        mv.addObject("user",user);
        mv.addObject("list",list);
        mv.setViewName("user-role-add");
        return mv;
    }
    @RequestMapping("user-role-add.go")
    public String addRoles(String[] ids,String userId)throws Exception{
        String[] uid=roleService.queryUidByUid(userId);
        int num=-1;
        if(uid.length==0){
            for (String rid:ids
                 ) {
            num=roleService.addRidAndUid(userId,rid);
            if(num<1){
                return null;
            }
            }
        }else {
            String[] rid=roleService.queryRidByUid(userId);
            int a=0;
            for (int i=0;i<ids.length;i++){
                for (int j=0;j<rid.length;j++){
                    if(ids[i].equals(rid[j])){
                        a=1;
                    }
                }
                if(a==0){
                    roleService.addRidAndUid(userId,ids[i]);
                }
            }
        }
        return "redirect:/user-list.do/1/3";
    }
    @GetMapping("/role-list.do/{pageNo}/{pageSize}")
    public ModelAndView queryAllRoles(@PathVariable("pageNo") String pageNo,@PathVariable("pageSize") String pageSize)throws Exception{
        ModelAndView mv=new ModelAndView();
        PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        List<Role> list=roleService.findAllRoles();
        PageInfo pageInfo = new PageInfo<>(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role-list");
        return mv;
    }
    @RequestMapping("/save.go")
    public String addRole(Role role)throws Exception{
        Integer rs= roleService.addRole(role);
        if(rs==1){
            return "redirect:/role-list.do/1/3";
        }else {
            return null;
        }
    }
    @RequestMapping("findByRoleId/{roleId}/{pageNo}/{pageSize}")
    public ModelAndView queryUserMsg(@PathVariable("roleId")String roleId,@PathVariable("pageNo") String pageNo,@PathVariable("pageSize") String pageSize)throws  Exception{
        ModelAndView mv = new ModelAndView();
        PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        List<Permission> list=permissionService.findPermissionByRid(roleId);
        PageInfo pageInfo = new PageInfo<>(list);
        Role role=roleService.queryRoleById(roleId);
        mv.addObject("role",role);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role-show");
        return mv;
    }
    @RequestMapping("deleteRole.do/{roleId}")
    public String deleteRole(@PathVariable("roleId")String roleId)throws Exception{
        roleService.deleteRoleById(roleId);
        return "redirect:/role-list.do/1/3";
    }
    @RequestMapping("showPermission.do/{roleId}")
    public ModelAndView showPermission(@PathVariable("roleId")String roleId)throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Permission> list=permissionService.findAllPermissions();
        mv.addObject("list",list);
        mv.addObject("roleId",roleId);
        mv.setViewName("role-permission-add");
        return mv;
    }
    @RequestMapping("/role/addPermissionToRole.do/{roleId}")
    public String addPermission(String [] ids,@PathVariable("roleId")String roleId)throws Exception{

        List<Permission> list=permissionService.findPermissionByRid(roleId);
        int num=-1;
        if(list.size()==0){
            for (String pId:ids
            ) {
                num=permissionService.addPermissionRole(pId,roleId);
                if(num<1){
                    return null;
                }
            }
        }else {
            List<Permission> list1=permissionService.findPermissionByRid(roleId);
            int a=0;
            for (int i=0;i<ids.length;i++){
                for (int j=0;j<list1.size();j++){
                    if(ids[i].equals(list1.get(j).getId())){
                        a=1;
                    }
                }
                if(a==0){
                    roleService.addRidAndUid(ids[i],roleId);
                }
            }
        }
        return "redirect:/role-list.do/1/3";
    }

}
