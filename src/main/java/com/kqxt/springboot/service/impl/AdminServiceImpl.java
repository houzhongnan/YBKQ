package com.kqxt.springboot.service.impl;

import com.kqxt.springboot.dao.AdminDao;
import com.kqxt.springboot.model.Admin;
import com.kqxt.springboot.model.base.PageObject;
import com.kqxt.springboot.model.exception.MyFormException;
import com.kqxt.springboot.model.query.AdminQuery;
import com.kqxt.springboot.service.AdminService;
import com.kqxt.springboot.util.SHA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin login(String a_username, String a_password) {

        return adminDao.login(a_username,a_password);
    }

    @Override
    public Admin getAdmin(Long id) {
        Admin admin=null;
        if (id!=null){
            admin=adminDao.getAdmin(id);
        }
        return admin;
    }
    /**
     * 获取数据库数据
     * */
    @Override
    public List<Admin> getAdmins() {
        return adminDao.getAdmins();
    }

    @Override
    public PageObject searchAdmins(Integer page, Integer limit, AdminQuery adminQuery) {
        PageObject pageObject=new PageObject(limit,page,adminDao.querySize(adminQuery));
        pageObject.setList(adminDao.query(pageObject.getOffset(),pageObject.getLimit(),adminQuery));
        return pageObject;
    }

    @Override
    public void addAdmin(@Valid @NotNull Admin admin) {
        if (admin!=null){
            if (admin.getA_username()==null){
                throw new MyFormException("账号添加失败：管理员账户名不可以为空！");
            }
            if (admin.getName()==null){
                throw new MyFormException("账号添加失败：管理员姓名不可以为空！");
            }
            if (admin.getA_position()==null){
                throw new MyFormException("账号添加失败：管理员职位不可以为空！");
            }
            if (admin.getE_mail()==null){
                throw new MyFormException("账号添加失败：管理员邮箱不可以为空！");
            }
            if (admin.getA_addingtime()==null){
                throw new MyFormException("账号添加失败：管理员加入易班时间不可以为空！");
            }
            if (adminDao.countUsername(admin.getA_username())==0){
                admin.setId(null); //主键自增
                admin.setA_password(SHA.getResult("123456a"));
                adminDao.save(admin);
            }else {
                throw new MyFormException("账号添加失败：管理员账号已存在，请重新输入！");
            }
        }

    }

    /*@Override
    public PageObject searchuAdmins(Integer page, Integer limit, AdminQuery adminQuery) {
        PageObject pageObject=new PageObject(limit,page,adminDao.querySize(adminQuery));
        pageObject.setLimit(adminDao.query(pageObject.getOffset(),pageObject.getLimit(),adminQuery));

        return pageObject;
    }*/


}
