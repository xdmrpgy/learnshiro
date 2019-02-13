package com.roypan.learnshiro.controller;

import com.roypan.learnshiro.entity.User;
import com.roypan.learnshiro.service.UserService;
import com.roypan.learnshiro.utils.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author by Roy Pan
 */
@RestController
@RequestMapping
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login page";
    }

    @GetMapping("/unauthc")
    public String unauthc(){
        return "unauthc page";
    }

    @GetMapping("/doLogin")
    public String doLogin(@RequestParam String userName,@RequestParam String password){
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ice){
            return "password error!";
        } catch (UnknownAccountException uae){
            return "userName error!";
        }

        User user = userService.queryByUserName(userName);
        subject.getSession().setAttribute("user",user);
        return "LOGIN SUCCESSFULLY!";
    }

    @GetMapping("/register")
    public String register(@RequestParam String userName,@RequestParam String password){
        /*User u = userService.queryByUserName(userName);
        if(u != null){
            return "user already exists";
        }*/
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        PasswordHelper.encryptPassword(user);
        userService.addUser(user);
        return "REGISTER SUCCESSFULLY!";
    }
}
