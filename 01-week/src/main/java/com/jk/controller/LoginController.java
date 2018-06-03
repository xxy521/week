package com.jk.controller;

import com.jk.entity.User;
import com.jk.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserService userService;

    //验重
    @ResponseBody
    @RequestMapping("/checkUserName")
    public String checkUserName(String username){
        User dbuser=userService.checkUserName(username);

        if(dbuser==null){
            //用户名或密码不存在
            return "false";
        }else{
            return "true";
        }

    }
    //登录
    @ResponseBody
    @RequestMapping("/loginUser")
    public String loginUser(User user,HttpSession session){
        //与验重共用一个方法就行了
        User dbuser=userService.checkUserName(user.getUsername());


        //根据用户名验证密码
        if(dbuser.getUsername()!=null){
            //验证密码是否正确
            if(dbuser.getUserpass().equals(user.getUserpass())){

                //将用户id存到session中了
                session.setAttribute("dbuser", dbuser);

                System.out.println("session中为："+dbuser);

                return  "true";
            }
        }
        return "false";
    }






}
