package com.junjie.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @RequestMapping("/ok")
    public String tt() {
        System.out.println("=====ok===成功=========");
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        System.out.println("=====成功=========");
        return "login";
    }

    @RequestMapping("/unAuth")
    public String unAuth() {
        System.out.println("=====成功=========");
        return "unAuth";
    }


    /**
     * 登陆 方法
     *
     * @return
     */
    @RequestMapping("/login")
    public String login(String username, String paw, Model model) {
        Map<String, String> map = new HashMap<>();
        System.out.println("=====登陆方法执行=========");
        //获取subject
        Subject subject = SecurityUtils.getSubject();

        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, paw);

        //执行登陆方法a
        try {
            subject.login(token);
            //登陆成功
            return "redirect:/ok";
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码不正确");
            return "login";
        }


    }


    //测试过滤的两个页面
    @RequestMapping("/user/add")
    public String add() {
        System.out.println("=====add成功=========");
        return "/user/add";
    }

    @RequestMapping("/user/update")
    public String update() {
        System.out.println("=====update成功=========");
        return "/user/update";
    }


}
