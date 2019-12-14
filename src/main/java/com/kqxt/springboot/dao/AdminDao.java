package com.kqxt.springboot.dao;

import com.kqxt.springboot.model.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDao extends IBaseDao<Admin> {

    /**
     * 登录操作
     * */

    Admin login(@Param(value = "a_username") String a_username, @Param(value = "a_password") String a_password);

    /**
     * 根据id指定标识符
     * @param  id
     * */
    Admin getAdmin(Long id);

    /**
     * 读取管理员列表admin中的所有数据
     * */
   List<Admin> getAdmins();

   /**
    * 获取数据库中相同账户名的数量，用于判断创建账户时是否同名
    * @param  a_username
    * @return
    * */
   int countUsername(String a_username);

   /**
    * 查找在数据库中除了指定用户外，和该用户相同的个数
    * @param a_username
    * @param id 账户主键，除了该账号外
    * @return 返回相同用户的个数，0表示不重名
    * */
   int countOtherUsername(@Param("a_username") String a_username,@Param("id") Long id);

}
