package com.hhuc.webdesign.service;

import com.hhuc.webdesign.entity.User;
import com.hhuc.webdesign.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityService implements UserDetailsService {
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleService roleService;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(userName);
        Integer userId = user.getId();

        List<UserRole> userRoles = userRoleService.getUserRolesByUserID(userId);

        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (UserRole userRole:
             userRoles) {
            Integer roleId = userRole.getRoleId();
            String roleName = roleService.getRoleByRoleId(roleId).getRoleName();
            authorityList.add(new SimpleGrantedAuthority(roleName));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPwd(),authorityList);
    }
}
