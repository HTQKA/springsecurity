package com.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @program: springsecurity
 * @ClassName JwtUtil
 * @description: JWT工具类
 * @author: 徐杨顺
 * @create: 2022-09-07 14:17
 * @Version 1.0
 **/
public class JwtUtil {

    //有效期
    public static final Long JWT_TTL = 1000 * 60 * 60L;//一个小时
    //设置密钥明文
    public static final String JWT_KEY = "xysimj";

    public static String getUUID() {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;

    }

    /**
     * 生成JWT
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder build = getJwtBuild(subject, null, getUUID());
        return build.compact();
    }

    /**
     * 生成JWT
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject,Long ttlMillis) {
        JwtBuilder builder = getJwtBuild(subject, ttlMillis, getUUID());
        return builder.compact();
    }

    /**
     *
     * @param subject
     * @param ttlMillis
     * @param uuid
     * @return
     */
    private static JwtBuilder getJwtBuild(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)//唯一的id
                .setSubject(subject)//主题 可以是JSON数据
                .setIssuer("xysimj")//签发者
                .setIssuedAt(now)//签发时间
                .signWith(signatureAlgorithm, secretKey)//使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);//过期时间

    }

    /**
     * 创建token
     * @param id
     * @param subject
     * @param ttMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttMillis) {
        JwtBuilder jwtBuild = getJwtBuild(subject, ttMillis, id);
        return jwtBuild.compact();
    }

    /**
     * 生成加密后的密钥
     * @return
     */
    public static SecretKey generalKey() {
        byte[] decode = Base64.getDecoder().decode(JWT_KEY);
        SecretKey key = new SecretKeySpec(decode, 0, decode.length,"AES");
        return key;
    }

    /**
     * 解析JWT
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)//密钥
                .parseClaimsJws(jwt)//jwt字符串
                .getBody();
    }
}
