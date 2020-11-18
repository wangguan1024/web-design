package com.hhuc.webdesign.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hhuc.webdesign.mapper.UserRoleMapper;
import com.hhuc.webdesign.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;

    public List<UserRole> getUserRolesByUserID(Integer userId){
        return userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("user_id", userId));
    }

    public int insertNewUser(Integer userId){
        UserRole userRole = new UserRole();
        userRole.setRoleId(1);
        userRole.setUserId(userId);
        return userRoleMapper.insert(userRole);
    }
}
