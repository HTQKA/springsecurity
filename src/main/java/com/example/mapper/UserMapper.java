package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @program: springsecurity
 * @ClassName UserMapper
 * @description:
 * @author: 徐杨顺
 * @create: 2022-09-07 15:36
 * @Version 1.0
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {

}
