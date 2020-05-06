package com.example.graduation_3.config;

import com.example.graduation_3.dto.UserDTO;
import com.example.graduation_3.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 进行数据库操作
 * @author Cy
 * @data 2020/5/2 - 1:09
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /**
         * UserDetails
         *  用户信息：用户的权限集， 默认需要添加ROLE_ 前缀；用户的加密后的密码，不加密会使用{noop}前缀；
         *  应用内唯一的用户名；账户是否过期；账户是否锁定；凭证是否过期；用户是否可用；
         */
        UserDetails user = null;
        UserDTO loginUser = null;
        try {
            loginUser = userService.getUserByName(username);
            //数据库中密码已加密，此处可以不用加密
            user = new User(loginUser.getName(),loginUser.getPassword(),getAuthorities(loginUser.getRole()));
        }catch (Exception e){
            e.printStackTrace();
            throw new UsernameNotFoundException("搜索用户信息错误");
        }
        return user;
    }

    /**
     * 获得角色/权限信息，方便后续进行验证
     * @param role
     * @return
     */
    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
        //GrantedAuthority grantedAuthority =  new SimpleGrantedAuthority(user.getRole());
        authList.add(new SimpleGrantedAuthority(role));
        return authList;
    }
}