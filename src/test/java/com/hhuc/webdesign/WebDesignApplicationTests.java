package com.hhuc.webdesign;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hhuc.webdesign.dao.ArticleDao;
import com.hhuc.webdesign.dao.UserDao;
import com.hhuc.webdesign.entity.Article;
import com.hhuc.webdesign.entity.User;
import com.hhuc.webdesign.entity.UserRole;
import com.hhuc.webdesign.service.RoleService;
import com.hhuc.webdesign.service.UserRoleService;
import com.hhuc.webdesign.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class WebDesignApplicationTests {
    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RoleService roleService;

    @Autowired
    ArticleDao articleDao;

    @Test
    void contextLoads() throws JSONException {
        Article article = new Article();
        article.setTitle("test_title");
        article.setContent("hello world");
        article.setOverview("hewo");
        article.setUserId(1);
        articleDao.insert(article);
    }

    @Test
    void test(){
        User user = userService.getUserByUserName("test");
        Integer userId = user.getId();

        List<UserRole> userRoles = userRoleService.getUserRolesByUserID(userId);

        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (UserRole userRole:
                userRoles) {
            Integer roleId = userRole.getRoleId();
            String roleName = roleService.getRoleByRoleId(roleId).getRoleName();
            authorityList.add(new SimpleGrantedAuthority(roleName));
        }
        System.out.println(authorityList);
    }

}
