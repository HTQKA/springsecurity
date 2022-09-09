package com.example;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SpringsecurityApplicationTest {
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Autowired
    PasswordEncoder passwordEncoder;
    @Test
    void password() {
        String xysimj = passwordEncoder.encode("xysimj");
        String xysimj1 = passwordEncoder.encode("xysimj");
        System.out.println(xysimj);
        System.out.println(xysimj1);
        System.out.println(passwordEncoder.matches("xysimj", xysimj));
        System.out.println(passwordEncoder.matches("xysimj", xysimj1));
    }
}
