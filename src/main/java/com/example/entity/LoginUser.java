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
