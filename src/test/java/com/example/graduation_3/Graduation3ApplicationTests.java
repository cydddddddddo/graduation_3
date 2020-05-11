package com.example.graduation_3;

import com.example.graduation_3.controller.MenuController;
import com.example.graduation_3.dto.CareerDTO;
import com.example.graduation_3.dto.MemberDTO;
import com.example.graduation_3.dto.PermissionDTO;
import com.example.graduation_3.dto.UserDTO;
import com.example.graduation_3.service.CareerServiceImpl;
import com.example.graduation_3.service.MemberServiceImpl;
import com.example.graduation_3.service.PermissionServiceImpl;
import com.example.graduation_3.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Graduation3ApplicationTests {

    @Autowired
    private CareerServiceImpl careerService;
    @Test
    void contextLoads() {


        System.out.println("ok");
    }

}
