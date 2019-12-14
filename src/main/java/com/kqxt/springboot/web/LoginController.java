package com.kqxt.springboot.web;

import com.kqxt.springboot.model.Admin;
import com.kqxt.springboot.service.AdminService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.management.ObjectName;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "易学系统后台登录")
@Controller
public class LoginController {

    @Resource
    private AdminService adminService;

    /*
     * 跳转到登录页面
     * */
    @RequestMapping(value = "toLogin")
    public String toLogin() {

        return "login";
    }

    /*
     * 前台登录处理
     * */
    @ResponseBody
    @RequestMapping(value = "doLogin")
    public Map<String, Object> login(String a_username, String a_password, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        /* System.out.println(name);*/
       // adminService.getAdmins();
        Admin admin = adminService.login(a_username, a_password);
        System.out.println(a_username);
        if (admin != null) {
            session.setAttribute("admin", admin);
            map.put("status", 1);

        } else {
            map.put("status", -1);
            map.put("myMessage", "登录失败：用户名和密码错误");
        }
        return map;

    }

    /*注销*/
    @RequestMapping(value = "logout")
    public String logout(HttpSession session) {
        session.removeAttribute("admin");
        return "login";
    }

  /*跳转到前台首页*/
    @RequestMapping(value = "toIndex")
    public String Index() {
        return "index";
    }

}
