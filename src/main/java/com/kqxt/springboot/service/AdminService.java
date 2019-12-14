package com.kqxt.springboot.service;

import com.kqxt.springboot.model.Admin;
import com.kqxt.springboot.model.base.PageObject;

import com.kqxt.springboot.model.query.AdminQuery;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface AdminService {
    /**
     * 处理登录操作
     * @param a_username
     * @param a_password
     * @return
     * */

    Admin login(String a_username, String a_password);

    /**
     * 根据id指定对应的进来
     * @param  id
     * @return
     * */
    Admin getAdmin(Long id);

    /**
     * 获取管理员列表中的所有记录
     * */
    List<Admin> getAdmins();

    /**
     * 分页查询账户
     * @param page 当前页面
     * @param  limit 每页最多显示的记录数
     * @param  adminQuery 查询条件类
     * */
   PageObject searchAdmins(Integer page,Integer limit,AdminQuery adminQuery);

   /**
    * 添加管理员
    * 账户名和密码不可以为空
    * @param  admin
    * */
   void addAdmin(@Valid @NotNull Admin admin);   //@Valid注解可以实现数据的验证，你可以定义实体，在实体的属性上添加校验规则，而在API接收数据时添加@valid关键字，这时你的实体将会开启一个校验的功能。


}
