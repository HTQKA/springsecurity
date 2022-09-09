# SpringSecurity

## SpringSecurity介绍

SpringSecurity时Springboot家族中的一个安全框架。

Spring是非常流行和成功的Java应用开发框架，Spring Security正是Spring家族中的成员。Spring Security基于Spring框架，提供了一套Web应用安全性的完整解决方案。

Shiro也是一个安全框架，提供了很多安全功能。但是Shiro已经比较老了，旧的项目中可能还在使用。

目前大多数项目都使用的是SpringSecurity。

### 登录鉴权

一般来说，Web应用的安全性包括**用户认证（Authentication）\**和\**用户授权（Authorization**）两个部分，这两点也是Spring Security重要核心功能。

登录：验证当前访问的用户是不是本系统中的用户，确定是那个一具体的用户。

鉴权：经过认证之后。判断当前登录用户有没有权限来执行某个操作。

所以SpringSecurity 当中，也有认证和鉴权两大核心功能。

Spring Security重要核心功能。

1. **用户认证**指的是：验证某个用户是否为系统中的合法主体，也就是说用户能否访问该系统。用户认证一般要求用户提供用户名和密码。系统通过校验用户名和密码来完成认证过程。**通俗点说就是系统认为用户是否能登录**
2. **用户授权**指的是验证某个用户是否有权限执行某个操作。在一个系统中，不同用户所具有的权限是不同的。比如对一个文件来说，有的用户只能进行读取，而有的用户可以进行修改。一般来说，系统会为不同的用户分配不同的角色，而每个角色则对应一系列的权限。**通俗点讲就是系统判断用户是否有权限去做某些事情。**



## SpringSecurity和shiro对比

**Spring Security**
Spring 技术栈的组成部分。

通过提供完整可扩展的认证和授权支持保护你的应用程序。
https://spring.io/projects/spring-security
SpringSecurity 特点：

- 和 Spring 无缝整合。
- 全面的权限控制。
- 专门为 Web 开发而设计。
  - 旧版本不能脱离 Web 环境使用。
  - 新版本对整个框架进行了分层抽取，分成了核心模块和 Web 模块。单独引入核心模块就可以脱离 Web 环境。
- 重量级。

**Shiro**

Apache 旗下的轻量级权限控制框架。

特点：

- 轻量级。 Shiro 主张的理念是把复杂的事情变简单。针对对性能有更高要求的互联网应用有更好表现。
- 通用性。
  - 好处：不局限于 Web 环境，可以脱离 Web 环境使用。
  - 缺陷：在 Web 环境下一些特定的需求需要手动编写代码定制。

Spring Security 是 Spring 家族中的一个安全管理框架，实际上，在 Spring Boot 出现之前， Spring Security 就已经发展了多年了，但是使用的并不多，安全管理这个领域，一直是 Shiro 的天下。

相对于 Shiro，在 SSM 中整合 Spring Security 都是比较麻烦的操作，所以， SpringSecurity 虽然功能比 Shiro 强大，但是使用反而没有 Shiro 多（ Shiro 虽然功能没有Spring Security 多，但是对于大部分项目而言， Shiro 也够用了）。

自从有了 Spring Boot 之后， Spring Boot 对于 Spring Security 提供了自动化配置方
案，可以使用更少的配置来使用 Spring Security。因此，一般来说，常见的安全管理技术栈的组合是这样的：
• SSM + Shiro
• Spring Boot/Spring Cloud + Spring Security
**以上只是一个推荐的组合而已，如果单纯从技术上来说，无论怎么组合，都是可以运行
的。**

## 入门

### 准备web项目

1.1创建一个SpringBoot web项目

idea快速构建

```xml

	<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>cn.itlym.shoulder</groupId>
            <artifactId>lombok</artifactId>
            <version>0.1</version>
        </dependency>
  <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
```

创建一个controller

```java

@RestController
@RequestMapping("demo")
public class DemoController {
    @GetMapping("hello")
    public String hello() {
        return "Hello Spring Security";
    }
}   
```

浏览器输入localhost:8080/demo/hello 访问

### 引入SpringSecurity

SpringBoot引入security之后，访问接口就必须要登录

```XML

  		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
```

加入SpringSecurity之后，访问项目资源就必须要登陆，系统会默认条状到SpringSecurity默认登录页面

再次访问localhost:8080/demo/hello，直接跳转到登录页面

![image-20220909134617241](C:\Users\TheShun\AppData\Roaming\Typora\typora-user-images\image-20220909134617241.png)



输入系统默认的用户名密码登录

用户名：user

密码：控制台查看:我的是28d5ffbc-a19e-4e15-ba45-35de332db69d

```

Using generated security password: 28d5ffbc-a19e-4e15-ba45-35de332db69d
```



成功访问到资源

![image-20220909134757968](C:\Users\TheShun\AppData\Roaming\Typora\typora-user-images\image-20220909134757968.png)



除此之外SpringSecurity还自带推出功能

页面中访问http://localhost:8080/logout

![image-20220909135013594](C:\Users\TheShun\AppData\Roaming\Typora\typora-user-images\image-20220909135013594.png)



点面积logout即可推出

## 认证



### web登录流程

![img](https://www.ydlclass.com/doc21xnv/assets/image-20220820185333664.af9918b3.png)



修改意见

1. 现在使用的是security自带的登陆页面，比较丑。 想换成自己项目的，优化的登录页。
2. 用户使用的是security给的用户名和密码。 想真实地去数据库里，tb_user获取真实的用户名和密码。
3. security自带的cookie\session模式。 想自己生成jwt，无状态登陆。
4. 前端页面怎么携带jwt。 想请求头里带上。
5. 鉴权操作完全没有。 想鉴权做完善。

### 看源码

spring security就是通过一个过滤器链，内置了关于spring security的16个过滤器

### SpringSecurity核心过滤器

springsecurity就是一个过滤器链，内置了关于springsecurity的16的过滤器。

- UserNamePassWordAuthenticationFilter：处理我们登录页面输入的用户名密码是否输入正确
- ExceptionTransactionFilter：处理前面几个过滤器中，有了问题，抛出错误，不让用户登录
- FilterSecurityInterceptor：权限校验的拦截器





| 抽象类或者接口                            | 实现类                               | 作用               |
| ----------------------------------------- | ------------------------------------ | ------------------ |
| AbstractAuthenticationProcessFilter       | UsernamePasswordAuthenticationFilter |                    |
| AuthenticationManager                     | ProviderManager                      |                    |
| AbstractUser DetailAuthenticationProvider | DaoAuthentictionProvider             | 根据用户名检索用户 |
| AbstractAuthenticationProcessionFilter    | InMemoryDetailsManager               |                    |



![image-20220821175458416](https://www.ydlclass.com/doc21xnv/assets/image-20220821175458416.57c8e429.png)







### 设计思路

登录

1. 自定义登录接口
   - 调用prodivermanager auth方法
   - 登录成功成功jwt
   - 存入redis
2. 自定义usertdetailsManager实现类
   - 从数据库获取系统用户

访问资源：自定义认证过滤器

- 获取token
- 从token中获取用户信息
- 存入SecurityContextHolder



## JWT简介

JSON Web Token (JWT) 是一个非常轻巧的规范，这是一个规范允许我们使用JWT在用户和服务器之间传递安全可靠的消息。

好处：不需要服务器端存session，无状态的

一个JWT实际上就是一个字符串，它由三部分组成，头部，载荷，签名。asdf.asdf.asdf 

特点：可以看到，但是不能篡改，因为第三部分用了密钥

**头部（header）**

头部用于描述改JWT的最基本的信息，类如其类型以及签名所用的算法等等。这个也可以被表示成一个JSON对象。

```json
{"type":"JWT","alg":"HS256"}
```

.

   在头部指明了前面算法是HS256算法，我们进行BASE算法http：



**载荷（palyLoad）**

载荷就是存放有效信息的地方

定义一个playload

```json
{"sub":"123","name":"xys","admin":true,"age":18}
```

然后对其进行base64加密，得到JWT第二部分

```json

```



签证（signature）

JWT的第三部分是一个鉴证信息，这个签证信息由三部分组成：

- hearder（base64的后）
- playload（base64的后）
- secret

这个部分需要base64加密后的header和base64加密后的palyload使用，连接组成的字符串，然后通过header中声明的加密方法进行加盐secert组合加密，然后构成了jwt的第三部分

```,
hs256{"第一部分。第二部分.第三部分",secert }
```



JWT签发于验证Token

jJWT是一个提供端到端的JWT创建和验证的Java库，永远开源和免费，JJWT

很容易使用和理解。他被设计成了一个以建筑为中心的流畅界面，隐藏了内部的复杂性



```java
package com.example.controller;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DemoControllerTest {

    public static void main(String[] args) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("666")//设置id
                .setSubject("testJWT")//设置主题
                .setIssuedAt(new Date())//签发日期
                .signWith(SignatureAlgorithm.HS256, "xysimj");//设置加密算法和密钥,注意这里密钥太短会出异常
        String compact = jwtBuilder.compact();
        System.out.println(compact);
    }
}

```



生成的JWT密钥

```java
eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiJ0ZXN0SldUIiwiaWF0IjoxNjYyNTI3ODIyfQ.dYsdrNSZotsi-4kjbFwwCPcwh35okvIMk5zfW0HGAPw

```



JWT解码

```java
        //JWT解密
        String xys = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiJ0ZXN0SldUIiwiaWF0IjoxNjYyNTI4Mjg2LCJleHAiOjE2NjI1MjgyODh9.fJ5OsxJdabcBRZf1WA3lXlqcn5YsF25e0RQTuGZiIGA";
        Claims xysimj = Jwts.parser().setSigningKey("xysimj").parseClaimsJws(xys).getBody();
        System.out.println(xysimj);
```



返回的是,id,主题，签发日期，过期时间

```java
{jti=666, sub=testJWT, iat=1662528676, exp=1662528975}

```









## 自定义登录接口

1自定义一个controller登录接口

2放行自定义接口

3使用ProvideManager auth方法进行验证

4生成jwt给前端

5系统用户先关的信息放入redis





#### 创建token

1.pom.xml中引入token

```xml

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.0</version>
</dependency>
```



2测试类

```java

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
                .claim("UserId", 123)//
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

```



#### 添加fastjson，redis，jjwt依赖

```xml

<dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.83</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
            <version>2.7.3</version>
        </dependency>
```

添加redis相关配置

```java

package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @program: springsecurity
 * @ClassName RedisConfig
 * @description: redis配置
 * @author: 徐杨顺
 * @create: 2022-09-07 13:46
 * @Version 1.0
 **/
@Configuration
public class RedisConfig {

    @Bean
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);

        //使用StringRedisSerializer来序列化和反序列化Redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        //Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }


}

```



```java

package com.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @program: springsecurity
 * @ClassName RedisCache
 * @description:
 * @author: 徐杨顺
 * @create: 2022-09-07 15:18
 * @Version 1.0
 **/
@SuppressWarnings(value = { "unchecked", "rawtypes" })
@Component
public class RedisCache
{
    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key, final T value)
    {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit)
    {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout)
    {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @param unit 时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit)
    {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key)
    {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public boolean deleteObject(final String key)
    {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return
     */
    public long deleteObject(final Collection collection)
    {
        return redisTemplate.delete(collection);
    }

    /**
     * 缓存List数据
     *
     * @param key 缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> long setCacheList(final String key, final List<T> dataList)
    {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getCacheList(final String key)
    {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 缓存Set
     *
     * @param key 缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet)
    {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext())
        {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    /**
     * 获得缓存的set
     *
     * @param key
     * @return
     */
    public <T> Set<T> getCacheSet(final String key)
    {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap)
    {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> getCacheMap(final String key)
    {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往Hash中存入数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @param value 值
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value)
    {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public <T> T getCacheMapValue(final String key, final String hKey)
    {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 删除Hash中的数据
     *
     * @param key
     * @param hkey
     */
    public void delCacheMapValue(final String key, final String hkey)
    {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, hkey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys)
    {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern)
    {
        return redisTemplate.keys(pattern);
    }
}

```

#### 响应类

```java

package com.example.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @program: springsecurity
 * @ClassName ResponseResult
 * @description: 响应类
 * @author: 徐杨顺
 * @create: 2022-09-07 14:16
 * @Version 1.0
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
    private Integer code;//状态码
    private String msg;//提示信息，如果有错误。前端可以获取该字段进行提示
    private T data;

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

```



#### 工具类

```java

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

```



```java

package com.example.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: springsecurity
 * @ClassName WebUtis
 * @description:
 * @author: 徐杨顺
 * @create: 2022-09-07 15:19
 * @Version 1.0
 **/
public class WebUtils {

    public static String renderString(HttpServletResponse response, String str) {
        try { response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

```



#### 实体类

```java
package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: springsecurity
 * @ClassName User
 * @description: 用户实体类
 * @author: 徐杨顺
 * @create: 2022-09-07 15:23
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码
     */
    private String password;
    /**
     * 账号状态（0正常 1停用）
     */
    private String status;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phonenumber;
    /**
     * 用户性别（0男，1女，2未知）
     */
    private String sex;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户类型（0管理员，1普通用户）
     */
    private String userType;
    /**
     * 创建人的用户id
     */
    private Long createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private Long updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;
}


```



### 新建数据库

#### 数据校验以及数据库文件

自定义一个UserDetailsService的实现类,让SpringSecurity使用我们的实现类。从数据库中查询用户名和密码。

准备工作

ydl_security库中。我们先创建一个系统的用户表， 建表语句如下：

```sql
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '昵称',
  `password` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phonenumber` varchar(32) DEFAULT NULL COMMENT '手机号',
  `sex` char(1) DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(128) DEFAULT NULL COMMENT '头像',
  `user_type` char(1) NOT NULL DEFAULT '1' COMMENT '用户类型（0管理员，1普通用户）',
  `create_by` bigint DEFAULT NULL COMMENT '创建人的用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'itlils', 'IT李老师', '$2a$10$UNfN3sUdNmka5cxCmrWHf.EZs6yRTztTvwoLJWXGf6VjRz/ABJ9y2', '0', '123@qq.com', '13012345678', '0', 'a', '1', '1', '2022-08-20 18:52:41', '1', '2022-08-21 18:52:49', '0');
```



#### 引入MybatisPuls和mysql驱动的依赖

```xml

<dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.3.1.tmp</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.22</version>
        </dependency>
```

#### 配置数据库信息

```yml

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ydl_security?characterEncoding=utf-8&serverTimezone=UTC
    username: root
    password: 2811107845
    driver-class-name: com.mysql.cj.jdbc.Driver

mybatis-plus:
  mapper-locations: classpath*:/mapper/**/*.xml

```

#### 定义Mapper接口

mybatis-plus的Mapper都要继承BaseMapper,这样就可以使用接口中定义的一些查询方法

```java
public interface UserMapper extends BaseMapper<User> {
}
```



#### 修改User实体类

mybatis-plus注解，指定表名和主键

```
类名上加@TableName(value = "sys_user") ,id字段上加 @TableId
```

#### 启动类配置Mapper扫描

```java

package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mapper")
public class SpringsecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecurityApplication.class, args);
    }

}

```

#### 测试

```java

class SpringsecurityApplicationTest {
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
}
```

#### 结果

```java

[User(id=1, userName=xys, nickName=IT李老师, password=$2a$10$dlA/4cH7MuTx9VxGIUuEc.AiyGbTwgccexZ4GqSUkEpZMrJD8EP02, status=0, email=123@qq.com, phonenumber=13012345678, sex=0, avatar=a, userType=1, createBy=1, createTime=Sun Aug 21 02:52:41 CST 2022, updateBy=1, updateTime=Mon Aug 22 02:52:49 CST 2022, delFlag=0)]

```

#### 创建UserDetailsService实现类(重要)

创建UserDetailsService实现类，重写其中的方法。用户名从数据库中查询用户信息。

当security校验用户名，密码是会调用这个方法，来进行校验数据，到时候需要将权限角色信息全部存入LoginUser对象中封装

```java
package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.entity.LoginUser;
import com.example.entity.User;
import com.example.mapper.MenuMapper;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @program: springsecurity
 * @ClassName UserDetialService
 * @description:
 * @author: 徐杨顺
 * @create: 2022-09-07 15:50
 * @Version 1.0
 **/
@Service
public class UserDetailServiceImpl  implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    MenuMapper menuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.根据用户名从数据库中获取用户
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, username);//查询条件
        User user = userMapper.selectOne(wrapper);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名不存在");
        }
        //2.TODO 查询权限信息
        //List<String> list = new ArrayList<>(Arrays.asList("hello", "123"));
        //从数据库中查询权限
        List<String> perms = menuMapper.selectPermsByUserId(user.getId());
        //3.返回一个UserDetails
        return new LoginUser(user,perms);
    }
}

```



#### 创建UserDetails实现类

因为UserDetailsService方法的返回值是UserDetails类型，所以需要定义一个类，实现该接口，把用户信息封装在其中。



```java

package com.example.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: springsecurity
 * @ClassName LoginUser
 * @description:
 * @author: 徐杨顺
 * @create: 2022-09-07 16:10
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {

    private User user;

    public LoginUser(User user, List<String> permission) {
        this.user = user;
        this.permission = permission;
    }

    List<String> permission;
    @JSONField(serialize = false)//fastjson注解，不序列化该字段
    List<SimpleGrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //List<SimpleGrantedAuthority> list = new ArrayList<>();
        //for (String s : permission) {
        //    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(s);
        //    list.add(simpleGrantedAuthority);
        //}
        //高级写法，Stream流
        if (authorities != null) {
            return authorities;
        }
        authorities = permission.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

```

注意：如果要测试，需要往用户表中写入用户数据，并且如果你想让用户的密码是明文存储，需要在密码前加{noop}。例如

```java
{noop}密码明文
{noop}xysimj
```





#### 密码加密存储

1. 实际项目中不会把密码明文存储在数据库中
2. 默认使用PasswordEncoder要求数据库中的密码格式为{id}password。他会根据id去判断密码的加密方式。但是我们一般不会采用这种方式。所以需要替换PaawordEncoder

我们一般使用SpringSecurity为我们提供的BCryptPasswordEncoder

我们只需要使用把BCryptPasswordEncoder对象注入到Spring容器中，Spring Security就会使用改PaawordEncoder来进行密码校验

我们可以定义一个Spring Security配置类，Spring Security要求这个配置类要继承web SecurityConfigureAdapter

```JAVA
/**
 * @program: springsecurity
 * @ClassName SecurityConfig
 * @description:
 * @author: 徐杨顺
 * @create: 2022-09-07 16:32
 * @Version 1.0
 **/
@Configuration
public class SecurityConfig{

   
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

```





密码加密

1234 md5 ---》aioshdjoaisdj

1234 MD5（12345xys）----------》oiehfoiwhef  加盐

BCryptPasswordEncoder 自动加盐





#### WebSecurityConfigurerAdapter 过时问题

首先，过时也能用，如果看着不爽，可以使用如下方法。

以前我们自定义类继承自 WebSecurityConfigurerAdapter 来配置我们的 Spring Security，我们主要是配置两个东西：

- configure(HttpSecurity)
- configure(WebSecurity)

前者主要是配置 Spring Security 中的过滤器链，后者则主要是配置一些路径放行规则。

现在在 WebSecurityConfigurerAdapter 的注释中，人家已经把意思说的很明白了：

- 以后如果想要配置过滤器链，可以通过自定义 SecurityFilterChainBean来实现。
- 以后如果想要配置 WebSecurity，可以通过 WebSecurityCustomizerBean来实现。



```java

   @Bean
     public WebSecurityCustomizer webSecurityCustomizer() {
         return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
     }

	@Bean
    public  SecurityFilterChain securityFilterChain() {
        List&lt;Filter&gt; filters = new ArrayList&lt;&gt;();
        return new DefaultSecurityFilterChain(new AntPathRequestMatcher("/**"), filters);
    }

	//如果想好看
     @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http.authorizeRequests().antMatchers("/login").permitAll()
                 .antMatchers("/users/**", "/settings/**").hasAuthority("Admin")
                 .hasAnyAuthority("Admin", "Editor", "Salesperson")
                 .hasAnyAuthority("Admin", "Editor", "Salesperson", "Shipper")
                 .anyRequest().authenticated()
                 .and().formLogin()
                 .loginPage("/login")
                 .usernameParameter("email")
                 .permitAll()
                 .and()
                 .rememberMe().key("AbcdEfghIjklmNopQrsTuvXyz_0123456789")
                 .and()
                 .logout().permitAll();
  
         http.headers().frameOptions().sameOrigin();
  
         return http.build();
     }}   
```





## 自定义登录接口，并进认证和授权

### 登录接口

```java
package com.example.controller;

import com.example.entity.ResponseResult;
import com.example.entity.User;
import com.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @program: springsecurity
 * @ClassName LoginController
 * @description:
 * @author: 徐杨顺
 * @create: 2022-09-08 10:13
 * @Version 1.0
 **/

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    //登录接口
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }
    //登出接口
    @PostMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
    
    //测试接口，访问该接口需要demo权限
    @PostMapping("/user/demo")
    @PreAuthorize("hasAuthority('demo')")
    public ResponseResult demo(){
        return new ResponseResult(200,"demo");
    }
}

```



### service层逻辑，认证+用户信息生成JWT存入redis（注意用户登录接口直接放行不用进行授权验证）

1. 校验用户信息
2. 校验成功将用户信息存入authenticate中，可以通过authenticate.getPrincipal()获取用户信息的LoginUser对象
3. 根据UserId生成JWT并存入redis中

```java
package com.example.service;

import com.example.entity.LoginUser;
import com.example.entity.ResponseResult;
import com.example.entity.User;
import com.example.utils.JwtUtil;
import com.example.utils.RedisCache;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @program: springsecurity
 * @ClassName LoginService
 * @description:
 * @author: 徐杨顺
 * @create: 2022-09-08 10:15
 * @Version 1.0
 **/
@Service
public class LoginService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisCache redisCache;


    public ResponseResult login(User user) {
        //3使用ProviderManager auth方法进行验证
        //传入用户名密码，
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword(),null);
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //校验失败了，这里如果校验失败authenticate会返回空，校验成功的把用户信息存入authenticate中
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误！");
        }

        //4自己生成jwt给前端，authenticate.getPrincipal()从authenticate获取用户信息
        LoginUser loginUser= (LoginUser)(authenticate.getPrincipal());
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String,String> map=new HashMap();
        map.put("token",jwt);
        //5系统用户相关所有信息放入redis
        redisCache.setCacheObject("login:"+userId,loginUser);

        return new ResponseResult(200,"登陆成功",map);
    }


    public ResponseResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        redisCache.deleteObject("login:"+userId);

        return new ResponseResult(200,"退出成功！");
    }
}

```



### 授权（）

UserDetailsService实现类接口

用户登陆的时候会调用该方法，从数据库查询用户信息，包括密码和权限信息

权限信息这里查出来之后会和用户信息一起封装在loginUser对象中

```java
package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.entity.LoginUser;
import com.example.entity.User;
import com.example.mapper.MenuMapper;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @program: springsecurity
 * @ClassName UserDetialService
 * @description:
 * @author: 徐杨顺
 * @create: 2022-09-07 15:50
 * @Version 1.0
 **/
@Service
public class UserDetailServiceImpl  implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    MenuMapper menuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.根据用户名从数据库中获取用户
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, username);//查询条件
        User user = userMapper.selectOne(wrapper);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名不存在");
        }
        //2.TODO 查询权限信息
        //List<String> list = new ArrayList<>(Arrays.asList("hello", "123"));
        //从数据库中查询权限
        List<String> perms = menuMapper.selectPermsByUserId(user.getId());
        //3.返回一个UserDetails
        return new LoginUser(user,perms);
    }
}

```

#### 访问/user/demo该接口时会进行权限拦截


    //测试接口，访问该接口需要demo权限
    @PostMapping("/user/demo")
    @PreAuthorize("hasAuthority('demo')")
    public ResponseResult demo(){
        return new ResponseResult(200,"demo");
    }



会走自定义的过滤器，过滤器在Security配置类中加入到了security过滤链中

#### 权限拦截，进行授权

1.权限拦截先看请求时是否包含用户的token，不包含的化说明用户还未登录，请先登录

2.包含token之后，根据token信息解析出用户id

3.在根据用户id拿到存入redis中的用户信息，如果用户信息为空龙，或者token过期的话也需要重新登录

4.最重要的一步时从token中拿到用户信息和用户的权限信息，存入SecurityContextHolder中

5.security底层就是以下代码来验证权限

```java
   public final boolean hasAuthority(String authority) {
        return this.hasAnyAuthority(authority);
    }

    public final boolean hasAnyAuthority(String... authorities) {
        return this.hasAnyAuthorityName((String)null, authorities);
    } 
private boolean hasAnyAuthorityName(String prefix, String... roles) {
        Set<String> roleSet = this.getAuthoritySet();//获取用户权限信息
        String[] var4 = roles;//需要满足的权限
        int var5 = roles.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String role = var4[var6];
            String defaultedRole = getRoleWithDefaultPrefix(prefix, role);
            if (roleSet.contains(defaultedRole)) {
                return true;
            }
        }

        return false;
    }
```



```java
/**
 * @program: springsecurity
 * @ClassName JwtAuthenticationTokenFilter
 * @description:自定一过滤器，并在Security配置类中将这个自定一过滤器配加入的security过滤链的最前面
 * @author: 徐杨顺
 * @create: 2022-09-08 13:34
 * @Version 1.0
 **/
//OncePerRequestFilter,直走一次，在请求前
@Component
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启全局方法安全校验，在方法允许前进行权限校验，需要在方法前加@PreAuthorize("hasAuthority = '权限'")
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(request.getRequestURL());
        //1获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //放行，让后面的过滤器执行
            filterChain.doFilter(request, response);
            return;
        }
        //2解析token
        String id;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            id = claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("token不合法");
        }

        //3获取userId
        LoginUser loginUser = redisCache.getCacheObject("login:" + id);
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("当前用户未登录");
        }
        //4封装Authentication
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        //5存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行，让后面的过滤器执行
        filterChain.doFilter(request, response);
    }
}

```



#### security自带的权限校验方法

前面都是使用@PreAuthorize注解，然后在在其中使用的是hasAuthority方法进行校验。SpringSecurity还为我们提供了其它方法例如：hasAnyAuthority，hasRole，hasAnyRole等。

hasAuthority

```java

	private boolean hasAnyAuthorityName(String prefix, String... roles) { //接口要求的权限 pull123
		Set<String> roleSet = getAuthoritySet();    //获取登录人的所有权限 pull push
		for (String role : roles) {
			String defaultedRole = getRoleWithDefaultPrefix(prefix, role);
			if (roleSet.contains(defaultedRole)) {
				return true;
			}
		}
		return false;
	}
```

hasAnyAuthority方法可以传入多个权限，只有用户有其中任意一个权限都可以访问对应资源。

```java

	private boolean hasAnyAuthorityName(String prefix, String... roles) {
		Set<String> roleSet = getAuthoritySet();
		for (String role : roles) {
			String defaultedRole = getRoleWithDefaultPrefix(prefix, role);
			if (roleSet.contains(defaultedRole)) {
				return true;
			}
		}
		return false;
	}
```



 2 hasRole要求有对应的角色才可以访问，但是它内部会把我们传入的参数拼接上 **ROLE_** 后再去比较。所以这种情况下要用用户对应的权限也要有 **ROLE_** 这个前缀才可以。

```java

private boolean hasAnyAuthorityName(String prefix, String... roles) {
		Set<String> roleSet = getAuthoritySet();
		for (String role : roles) {
			String defaultedRole = getRoleWithDefaultPrefix(prefix, role);
			if (roleSet.contains(defaultedRole)) {
				return true;
			}
		}
		return false;
	}
```

 hasAnyRole 有任意的角色就可以访问。它内部也会把我们传入的参数拼接上 **ROLE_** 后再去比较。所以这种情况下要用用户对应的权限也要有 **ROLE_** 这个前缀才可以。

```java

	private boolean hasAnyAuthorityName(String prefix, String... roles) {
		Set<String> roleSet = getAuthoritySet();
		for (String role : roles) {
			String defaultedRole = getRoleWithDefaultPrefix(prefix, role);
			if (roleSet.contains(defaultedRole)) {
				return true;
			}
		}
		return false;
	}
```



#### 自定义权限校验方法

 SpringSecurity为我们提供了基于注解的权限控制方案，这也是我们项目中主要采用的方式。

 我们可以使用注解去指定访问对应的资源所需的权限。

 但是要使用它我们需要先开启相关配置。配置类中。

```java
@EnableGlobalMethodSecurity(prePostEnabled = true)

```

 然后就可以使用对应的注解。@PreAuthorize

```java

    //调用自定义权限校验
    @GetMapping("123")
    @PreAuthorize("@ex.hasAuthority('dev:code:push')")
    public String XYS123() {
        return "123";
    }
```



```java

package com.example.expression;

import com.example.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: springsecurity
 * @ClassName SGExpressionRoot
 * @description: 自定义权限校验
 * @author: 徐杨顺
 * @create: 2022-09-08 20:39
 * @Version 1.0
 **/
@Component("ex")
public class SGExpressionRoot {

    public boolean hasAuthority(String authority) {
        //1获取当前登录人的权限
        //获取当前登录人员信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取权限
        List<String> permission = loginUser.getPermission();
        //2判断当前登录人员是否有权限
        boolean contains = permission.contains(authority);
        return contains;
    }
}

```



#### 自定义认证失败处理方法

```java

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //给前端返回一个ResponseResult 的json
        ResponseResult responseResult = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "您的登录认证失败了，请从新登录");
        String json = JSON.toJSONString(responseResult);
        WebUtils.renderString(response, json);
    }
}
```



#### 自定义授权失败处理方法

```java

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //给前端返回一个ResponseResult 的json
        ResponseResult responseResult = new ResponseResult(HttpStatus.FORBIDDEN.value(), "没有权限，访问失败");
        String json = JSON.toJSONString(responseResult);
        WebUtils.renderString(response, json);
    }
}
```



自定义成功登出处理方法

```JAVA

@Component
public class LLSLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("注销成功");
    }
}
```



#### 在security中配置这些处理器，告诉security你想怎么处理

```java
   //自定义权限认证异常处理器。当security校验用户没有权限是，执行该过滤器
    @Autowired
    AccessDeniedHandlerImpl accessDeniedHandler;

    //自定义登录认证异常处理器，登录失败是执行改过滤器
    @Autowired
    AuthenticationEntryPointImpl authenticationEntryPoint;

    @Autowired
    LLSLogoutSuccessHandler llsLogoutSuccessHandler;
```



```java
  //告诉security如何处理异常
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint);
        //成功登出处理器
        http.logout().logoutSuccessHandler(llsLogoutSuccessHandler);
```

