package com.example.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DemoControllerTest {

    public static void main(String[] args) {
        //当前时间
        long l = System.currentTimeMillis();
        Date date = new Date(l + 10000);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("666")//设置id
                .setSubject("testJWT")//设置主题
                .setIssuedAt(new Date())//签发日期
                .claim("UserId", 123)//角色信息
                //.setExpiration(date)//过期时间
                .signWith(SignatureAlgorithm.HS256, "xysimj");//设置加密算法和密钥,注意这里密钥太短会出异常
        String compact = jwtBuilder.compact();
        System.out.println(compact);

        //JWT解密
       // String xys = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiJ0ZXN0SldUIiwiaWF0IjoxNjYyNTI4Mjg2LCJleHAiOjE2NjI1MjgyODh9.fJ5OsxJdabcBRZf1WA3lXlqcn5YsF25e0RQTuGZiIGA";
       // try {
       //     Thread.sleep(15000);//暂停十五秒
       // } catch (InterruptedException e) {
       //     e.printStackTrace();
       // }
        Claims xysimj = Jwts.parser().setSigningKey("xysimj").parseClaimsJws(compact).getBody();
        System.out.println(xysimj);
    }
}
