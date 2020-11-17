package com.hhuc.webdesign.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhuc.webdesign.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseMapper<User> {
}
