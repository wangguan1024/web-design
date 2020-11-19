package com.hhuc.webdesign.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hhuc.webdesign.entity.User;
import com.hhuc.webdesign.entity.UserRole;
import com.hhuc.webdesign.mapper.RoleMapper;
import com.hhuc.webdesign.mapper.UserMapper;
import com.hhuc.webdesign.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleMapper roleMapper;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", userName));
        Integer userId = user.getId();

        List<UserRole> userRoles = userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("user_id", userId));

        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (UserRole userRole :
                userRoles) {
            Integer roleId = userRole.getRoleId();
            String roleName = roleMapper.selectById(roleId).getRoleName();
            authorityList.add(new SimpleGrantedAuthority(roleName));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPwd(), authorityList);
    }

    public String getUserNameBySecurity(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
