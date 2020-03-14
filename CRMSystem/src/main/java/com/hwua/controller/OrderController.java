package com.hwua.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwua.pojo.Orders;
import com.hwua.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/orders-page-list.do/{pageNo}/{pageSize}")
    public ModelAndView findAllOrder(@PathVariable("pageNo") String pageNo, @PathVariable("pageSize") String pageSize)throws Exception{
        ModelAndView mv=new ModelAndView();
        PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        List<Orders> list=orderService.findAllOrder();
        PageInfo pageInfo = new PageInfo<>(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }
    @RequestMapping("/orders/findById.do/{id}")
    public ModelAndView findOrderMsg(@PathVariable("id") String id)throws Exception{
        ModelAndView mv=new ModelAndView();
        Orders order=orderService.findOrderById(id);
        mv.addObject("orders",order);
        mv.setViewName("orders-show");
        return mv;
    }
}
