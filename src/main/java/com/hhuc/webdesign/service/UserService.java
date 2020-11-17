package com.hhuc.webdesign.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hhuc.webdesign.dao.UserDao;
import com.hhuc.webdesign.entity.User;
import com.hhuc.webdesign.util.ReturnPkg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User getUserByUserName(String userName){
        return userDao.selectOne(new QueryWrapper<User>().eq("user_name",userName));
    }

    public ReturnPkg Insert(User user){
        if(userDao.selectOne(new QueryWrapper<User>().eq("user_name", user.getUserName()))!=null){
            return ReturnPkg.failed("用户名已被使用");
        }

        //改为密文存储，写入数据库
        String password = user.getPwd();
        String encodePassword = passwordEncoder.encode(password);
        user.setPwd(encodePassword);

        if(userDao.insert(user)==1){
            if(userRoleService.insertNewUser(getUserByUserName(user.getUserName()).getId())==1){
                return ReturnPkg.success();
            }
        }
        return ReturnPkg.failed("数据库操作异常");
    }

    public ReturnPkg Update(User user) {
        //改为密文存储，写入数据库
        String password = user.getPwd();
        String encodePassword = passwordEncoder.encode(password);
        user.setPwd(encodePassword);

        if(userDao.update(user, new QueryWrapper<User>().eq("user_name", user.getUserName()))==1) {
            return ReturnPkg.success("修改成功");
        }else{
            return ReturnPkg.failed("数据库操作异常");
        }
    }


}
