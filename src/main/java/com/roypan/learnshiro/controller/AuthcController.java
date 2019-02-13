package com.roypan.learnshiro.controller;

import com.roypan.learnshiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author by Roy Pan
 */
@RestController
@RequestMapping("/authc")
public class AuthcController {
    @GetMapping("/index")
    public String index(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        return user.toString();
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin page";
    }

    @GetMapping("/renewable")
    public String renewable(){
        return "renewable perms";
    }

    @GetMapping("/removeable")
    public String removeable(){
        return "removeable perms";
    }
}
