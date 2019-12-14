package com.kqxt.springboot.dao;

import com.kqxt.springboot.SpringbootApplicationTests;
import com.kqxt.springboot.model.Admin;
import org.junit.Test;


import javax.annotation.Resource;
import java.util.List;

public class AdminDaoTest extends SpringbootApplicationTests {
    @Resource
    private AdminDao adminDao;

    @Test
    public void getAdmins(){
        Admin admin=adminDao.getAdmin(201611250101L);
        System.out.println(admin.getA_username());
    }}

