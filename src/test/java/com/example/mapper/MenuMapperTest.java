package com.example.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MenuMapperTest {
    @Autowired
    MenuMapper menuMapper;

    @Test
    void test() {
        List<String> list = menuMapper.selectPermsByUserId1(1L);
        list.forEach(System.out::println);

    }

}
