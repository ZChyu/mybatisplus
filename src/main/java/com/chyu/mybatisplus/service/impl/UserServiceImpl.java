package com.chyu.mybatisplus.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chyu.mybatisplus.chyu_utils.PageUtil;
import com.chyu.mybatisplus.entity.User;
import com.chyu.mybatisplus.mapper.UserMapper;
import com.chyu.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chyu
 * @since 2019-04-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public User getUserOne(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", user.getId());
        User res = userMapper.selectOne(queryWrapper);
        return res;
    }

    @Override
    public JSONObject deleteOneById(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", user.getId());
        int flag = userMapper.delete(queryWrapper);
        JSONObject object = new JSONObject();
        object.put("data", flag);
        return object;

    }

    @Override
    public JSONObject addById(User user) {
        JSONObject object = new JSONObject();
        try {
            user.setPassword(md5(user.getPassword()));
            Boolean flag = this.save(user);
            object.put("data", flag);
            return object;
        } catch (Exception e) {
            object.put("data", "id已存在！请修改id再添加！");
            return object;
        }
    }

    @Override
    public JSONObject updateUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", user.getId());
        int flag = userMapper.update(user, queryWrapper);
        JSONObject object = new JSONObject();
        object.put("data", flag);
        return object;
    }

    @Override
    public JSONObject login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", user.getName()).eq("Password", md5(user.getPassword()));
        User userRes = userMapper.selectOne(queryWrapper);
        JSONObject object = new JSONObject();
        if (userRes != null) {
            object.put("data", true);
            return object;
        } else {
            object.put("data", false);
            return object;
        }
    }

    @Override
    public Object selectPageList(User user, PageUtil pageUtil) {
        JSONObject object = new JSONObject();
        try {
            Page<User> page = new Page<>(pageUtil.getCurrPage(), pageUtil.getPageSize());
            page.setRecords(userMapper.selectPageList(page, user));
            object.put("data",page.getRecords());
            object.put("total",page.getTotal());
            return object;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String md5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] bytes = md.digest(password.getBytes());
            String str = Base64.getEncoder().encodeToString(bytes);
            return str;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
