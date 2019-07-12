package com.chyu.mybatisplus.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chyu.mybatisplus.chyu_utils.PageUtil;
import com.chyu.mybatisplus.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chyu
 * @since 2019-04-30
 */
public interface UserService extends IService<User> {

    List<User> getUsers();

    User getUserOne(User user);

    JSONObject deleteOneById(User user);

    JSONObject addById(User user);

    JSONObject updateUser(User user);

    JSONObject login(User user);

    Object selectPageList(User user, PageUtil pageUtil);

}
