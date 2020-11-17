package com.hhuc.webdesign.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhuc.webdesign.entity.User;
import com.hhuc.webdesign.entity.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDao extends BaseMapper<UserRole> {
}
