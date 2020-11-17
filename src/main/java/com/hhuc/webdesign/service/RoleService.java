package com.hhuc.webdesign.service;


import com.hhuc.webdesign.dao.RoleDao;
import com.hhuc.webdesign.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;
    public Role getRoleByRoleId(Integer id){
        return roleDao.selectById(id);
    }
}
