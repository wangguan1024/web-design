package com.hhuc.webdesign.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hhuc.webdesign.dao.UserRoleDao;
import com.hhuc.webdesign.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserRoleService {
    @Autowired
    UserRoleDao userRoleDao;

    public List<UserRole> getUserRolesByUserID(Integer userId){
        return userRoleDao.selectList(new QueryWrapper<UserRole>().eq("user_id", userId));
    }

    public int insertNewUser(Integer userId){
        UserRole userRole = new UserRole();
        userRole.setRoleId(1);
        userRole.setUserId(userId);
        return userRoleDao.insert(userRole);
    }
}
