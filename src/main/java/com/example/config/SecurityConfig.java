package com.example.config;

import com.example.filter.JwtAuthenticationTokenFilter;
import com.example.handler.AccessDeniedHandlerImpl;
import com.example.handler.AuthenticationEntryPointImpl;
import com.example.handler.LLSLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

    //自定义拦截器，在SecurityFilterChain中配置拦截器什么时候执行
    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    //自定义权限认证异常处理器。当security校验用户没有权限是，执行该过滤器
    @Autowired
    AccessDeniedHandlerImpl accessDeniedHandler;

    //自定义登录认证异常处理器，登录失败是执行改过滤器
    @Autowired
    AuthenticationEntryPointImpl authenticationEntryPoint;

    @Autowired
    LLSLogoutSuccessHandler llsLogoutSuccessHandler;

    //加入BCryptPasswordEncoder加密，security会对密码默认进行加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * SecurityFilterChain 就是spring security中的过滤器链对象：并在该类对象下配置认证，授权规则,当一个请求 HttpServletRequest 进入
     * SecurityFilterChain 时，会通过 matches 方法来确定是否满足条件进入过滤器链，进而决定请求应该执行哪些过滤器
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                //.antMatchers("/user/login").anonymous()//anonymous，未登录才可以访问
                 .antMatchers("/user/login","/user/demo").permitAll()//登录，未登录都可以访问
                        .antMatchers("/demo/hello").hasAuthority("dev:code:push")//判断某个接口是都有这个权限
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
        //把自定义过滤器，放在UsernamePasswordAuthenticationFilter过滤器之前
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //告诉security如何处理异常
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint);
        //成功登出处理器
        http.logout().logoutSuccessHandler(llsLogoutSuccessHandler);
        //http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

        //允许跨域
        http.cors();
        return http.build();
    }

    //ioc容器中获取
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    //存储用户信息，将用户信息存入security中
    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
        return authenticationManager;
    }


}
