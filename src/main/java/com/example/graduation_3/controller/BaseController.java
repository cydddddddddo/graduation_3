package com.example.graduation_3.controller;

import com.example.graduation_3.dto.UserDTO;
import com.example.graduation_3.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Cy
 * @data 2020/5/3 - 18:09
 */
public class BaseController {

    @Autowired
    private UserServiceImpl userService;


    protected UserDTO getCurrentUser() {
        UserDTO dbUser = null;
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            dbUser = userService.getUserByName(userDetails.getUsername());
        } catch (Exception e) {
            return null;
        }

        return dbUser;
    }
}
