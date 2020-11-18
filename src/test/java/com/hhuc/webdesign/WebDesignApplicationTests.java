package com.hhuc.webdesign;

import com.hhuc.webdesign.mapper.ArticleMapper;
import com.hhuc.webdesign.entity.Article;
import com.hhuc.webdesign.entity.User;
import com.hhuc.webdesign.entity.UserRole;
import com.hhuc.webdesign.service.ArticleService;
import com.hhuc.webdesign.service.RoleService;
import com.hhuc.webdesign.service.UserRoleService;
import com.hhuc.webdesign.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
    ArticleMapper articleMapper;


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

    @Autowired
    ArticleService articleService;
    @Test
    void ArticleServiceTest(){
        Article article = new Article();
        article.setTitle("test1");
        article.setOverview("hhh");
        article.setContent("ooo");
    }

}
