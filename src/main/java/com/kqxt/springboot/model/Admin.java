package com.kqxt.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Admin  {
    private static final long serialVersionUID = 5952689219411916553L;

    private Long id;
    @Length(min = 6,max = 10,message = "管理员姓名的长度必须在{min}-{max}之间")
    private String name;  //姓名
    @Length(min = 8,max = 16,message = "管理员昵称的长度必须在{min}-{max}之间")
    private String a_name; //昵称
    @Length(min = 10,max = 11,message = "管理员账号的长度必须在{min}-{max}之间")
    private String a_username; //账号
    @Length(min = 6,max = 10,message = "管理员密码的长度必须在{min}-{max}之间")
    private String a_password; //密码

    private String a_position; //职位
    private String e_mail;     //邮箱
    private Date a_addingtime; //加入时间

    //非数据库字段
    //@ApiModelProperty(hidden = true)
    //private List<Role> roles;
   // private Integer[] roleIds;//用于接收前端传递过来的角色集合id。说明：接收“1,3,5”这样的类型（如果是js数组可以用tostring转换为这个类型），否则spring mvc数组参数无法接受

    public Admin(){
    }

    public Admin(Long id){
        super();
        this.id=id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_username() {
        return a_username;
    }

    public void setA_username(String a_username) {
        this.a_username = a_username;
    }

    public String getA_password() {
        return a_password;
    }

    public void setA_password(String a_password) {
        this.a_password = a_password;
    }

    public String getA_position() {
        return a_position;
    }

    public void setA_position(String a_position) {
        this.a_position = a_position;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public Date getA_addingtime() {
        return a_addingtime;
    }

    public void setA_addingtime(Date a_addingtime) {
        this.a_addingtime = a_addingtime;
    }





    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", a_name='" + a_name + '\'' +
                ", a_username='" + a_username + '\'' +
                ", a_password='" + a_password + '\'' +
                ", a_position='" + a_position + '\'' +
                ", e_mail='" + e_mail + '\'' +
                ", a_addingtime=" + a_addingtime +
                '}';
    }

}
