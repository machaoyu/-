package com.hwua.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwua.pojo.Users;
import com.hwua.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @ResponseBody
    @PostMapping("/login.do")
    public ModelAndView queryUser(Users user, HttpServletRequest request)throws Exception{
        ModelAndView mv = new ModelAndView();
        String info=null;
        //获取登录的主体对象
        Subject currentUser = SecurityUtils.getSubject();
        //判断当前用户是否验证通过(是否登录成功)
        if (!currentUser.isAuthenticated()) {
            //把用户名和密码封装成UsernamePasswordToken对象
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            try {
                currentUser.login(token);//当前用户进行登陆 参数类型:AuthenticationToken
            } catch (UnknownAccountException uae) {
                info="用户名不正确";
            } catch (IncorrectCredentialsException ice) {
                info="密码不正确";
            } catch (LockedAccountException lae) {
                info="账号被锁定";
            } catch (AuthenticationException ae) {
                info="联系管理员";
            }
        }
        if(info==null){
            mv.setViewName("main");
        }else{
            mv.addObject("info",info);
            mv.setViewName("login");
        }
        return mv;
        }

/*    @RequestMapping("/logout.do")
    public ModelAndView logout() throws  Exception{
      *//*  HttpSession session=request.getSession(false);*//*
 *//*       session.invalidate();*//*
        ModelAndView mv=new ModelAndView();
        SecurityUtils.getSubject().logout();
        mv.setViewName("login");
        return mv;
    }*/
    @GetMapping("/user-list.do/{pageNo}/{pageSize}")
    public ModelAndView queryAllUsers(@PathVariable("pageNo") String pageNo,@PathVariable("pageSize") String pageSize) throws Exception{
        ModelAndView mv = new ModelAndView();
        PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        List<Users> users = userService.findAllUsers();
        PageInfo pageInfo = new PageInfo<>(users);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }
    @RequestMapping("/save.do")
    public String addUser(Users user)throws Exception{
        Integer rs= userService.addUser(user);
        if(rs==1){
            return "redirect:/user-list.do/1/3";
        }else {
            return null;
        }
    }
    @RequestMapping("user-show.do/{userId}")
    public ModelAndView queryUserMsg(@PathVariable("userId")String userId)throws  Exception{
        ModelAndView mv = new ModelAndView();
        Users user=userService.findUserById(userId);
        mv.addObject("user",user);
        mv.setViewName("user-show1");
        return mv;
    }
}
