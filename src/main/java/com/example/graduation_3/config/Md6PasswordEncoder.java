package com.example.graduation_3.config;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.print.attribute.standard.NumberUp;

/**
 * 自定义加密类，未使用
 * @author Cy
 * @data 2020/5/2 - 17:26
 */
public class Md6PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return null;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
    }
}
