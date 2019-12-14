package com.kqxt.springboot.web.backstage.adminmanage;


import com.kqxt.springboot.model.Admin;

import com.kqxt.springboot.model.base.Constant;
import com.kqxt.springboot.model.base.JsonCode;
import com.kqxt.springboot.model.base.PageObject;
import com.kqxt.springboot.model.query.AdminQuery;
import com.kqxt.springboot.service.AdminService;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import java.util.HashMap;

import java.util.Map;

@Controller

public class AdminManageController {
    @Resource
    private AdminService adminService;

    @ApiOperation(value = "分页读取管理账户信息", notes = "如果page为空则默认是第一页;如果limit为空则采用服务器的默认数值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", required = false, dataType = "int", example = "1"),
            @ApiImplicitParam(name = "limit", value = "每页最多展示的记录数", required = false, dataType = "int", example = "20")
    })
    @GetMapping(value = "toAdminmanage")
    @ResponseBody
    public Map<String,Object> toAdminmanage(Integer limit, Integer page, AdminQuery adminQuery, HttpServletRequest request) {

        Map<String, Object> map = new HashMap<String, Object>();
        PageObject pageObject = adminService.searchAdmins(limit, page, adminQuery);
        map.put(Constant.JSON_TOTAL, pageObject.getTotalRecords());
        map.put(Constant.JSON_MESSAGE,"");
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        map.put(Constant.JSON_DATA, pageObject.getList());

        return map;
    }

    @RequestMapping(value ="toAdmin")
    public String toAdmin(HttpServletRequest request){
        request.setAttribute("list", adminService.getAdmins());
        return "adminmanage";
    }

    //添加管理员账号
    @GetMapping("toAddAdmin")
    public String toAddAdmin(){
        return "adminadd";
    }

    @ApiOperation(value = "添加账户", notes = "添加账户，要添加的管理账户对象，id不传值")
    @ApiImplicitParam(name = "admin", value = "管理账户信息，id、createTime不传值", paramType = "body", dataType="Admin",required = true)
    @PostMapping("doAddAdmin")
    public Map<String,Object> doAddAdmin(@RequestBody Admin admin){
        Map<String,Object> map=new HashMap<String, Object>();
        adminService.addAdmin(admin);
        map.put(Constant.JSON_CODE,JsonCode.SUCCESS.getValue());

        map.put(Constant.JSON_MESSAGE,"管理员添加成功！");
        return map;
    }

}