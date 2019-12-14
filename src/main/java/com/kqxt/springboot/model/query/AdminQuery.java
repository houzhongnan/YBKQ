package com.kqxt.springboot.model.query;

import com.kqxt.springboot.model.Admin;
/**
 * 作为Admin的查询条件
 */
public class AdminQuery extends Admin {

    private Integer roleId;  //角色查询
    private boolean accountNonLockedQuery;  //因为Admin中的是继承spring security的，只能用布尔基础类型，不能用于查询搜索

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public boolean isAccountNonLockedQuery() {
        return accountNonLockedQuery;
    }

    public void setAccountNonLockedQuery(boolean accountNonLockedQuery) {
        this.accountNonLockedQuery = accountNonLockedQuery;
    }
}
