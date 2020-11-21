package com.hhuc.webdesign.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hhuc.webdesign.entity.PasswordPkg;
import com.hhuc.webdesign.mapper.UserMapper;
import com.hhuc.webdesign.entity.User;
import com.hhuc.webdesign.util.ReturnPkg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    SecurityService securityService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User selectUserByUserName(String userName){
        return userMapper.selectOne(new QueryWrapper<User>().eq("user_name",userName));
    }

    @Transactional
    public ReturnPkg register(User user){
        if(userMapper.selectOne(new QueryWrapper<User>().eq("user_name", user.getUserName()))!=null){
            return ReturnPkg.failed("用户名已被使用");
        }
        //改为密文存储，写入数据库
        String password = user.getPwd();
        String encodePassword = passwordEncoder.encode(password);
        user.setPwd(encodePassword);

        if(userMapper.insert(user)==1){
            Integer userId = selectUserByUserName(user.getUserName()).getId();
            if(userRoleService.insertNewUser(userId)==1){
                return ReturnPkg.success();
            }
        }
        return ReturnPkg.failed("数据库操作异常");
    }

    public ReturnPkg changePwd(PasswordPkg passwordPkg) {
        String userName = securityService.getUserNameBySecurity();

        User thisUser = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", userName));
        String dbPwd = thisUser.getPwd();

//        String encodeOldPwd = passwordEncoder.encode(passwordPkg.getOldPwd());

        boolean matches = passwordEncoder.matches(passwordPkg.getOldPwd(), dbPwd);


        if(!matches){
            return ReturnPkg.failed("原密码错误");
        }else{
            //改为密文存储，写入数据库
            String newPwd = passwordPkg.getNewPwd();
            String encodeNewPwd = passwordEncoder.encode(newPwd);
            thisUser.setPwd(encodeNewPwd);

            if(userMapper.update(thisUser, new QueryWrapper<User>().eq("user_name", userName))==1) {
                return ReturnPkg.success("修改成功");
            }else{
                return ReturnPkg.failed("数据库操作异常");
            }
        }
    }

    public ReturnPkg updateUser(User user){
        return ReturnPkg.success( userMapper.updateById(user));
    }
}
