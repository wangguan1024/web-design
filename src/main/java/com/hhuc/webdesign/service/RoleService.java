package com.hhuc.webdesign.service;


import com.hhuc.webdesign.mapper.RoleMapper;
import com.hhuc.webdesign.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    public Role getRoleByRoleId(Integer id){
        return roleMapper.selectById(id);
    }
}
