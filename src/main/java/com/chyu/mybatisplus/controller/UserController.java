package com.chyu.mybatisplus.controller;

import com.chyu.mybatisplus.chyu_utils.PageUtil;
import com.chyu.mybatisplus.entity.User;
import com.chyu.mybatisplus.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chyu
 * @since 2019-04-30
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public List<User> getUser(){
        List<User> users= userService.getUsers();
        return users;
    }

    @RequestMapping("/selectonebyid")
    public Object selectOneById(User user){
        System.out.println(user.getId());
        return userService.getUserOne(user);
    }

    @RequestMapping("/deletebyid")
    public Object deleteById(User user) {
        JSONObject res  = userService.deleteOneById(user);
        return res;
    }

    @RequestMapping("/addbyid")
    public Object addById(User user){
        JSONObject res = userService.addById(user);
        return res;
    }

    @RequestMapping("/update")
    public Object updateUsers(User user){
        JSONObject res = userService.updateUser(user);
        return res;
    }

    @RequestMapping("/login")
    public Object login(User user){
        System.err.println(user.toString());
        JSONObject res = userService.login(user);
        return res;
    }

    @RequestMapping("/pagelist")
    public Object  findAllUser(PageUtil pageUtil){
        User user = new User();
        Object iPage = userService.selectPageList(user, pageUtil);
        return  iPage;
    }
}


