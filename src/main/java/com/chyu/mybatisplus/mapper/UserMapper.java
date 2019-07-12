package com.chyu.mybatisplus.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chyu.mybatisplus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chyu
 * @since 2019-05-01
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM  user")
    List<User> selectPageList(Page<User> page, User user);
}
