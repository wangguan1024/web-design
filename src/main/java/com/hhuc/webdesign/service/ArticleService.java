package com.hhuc.webdesign.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhuc.webdesign.entity.Article;
import com.hhuc.webdesign.entity.User;
import com.hhuc.webdesign.mapper.ArticleMapper;
import com.hhuc.webdesign.util.ReturnPkg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    UserService userService;

    /**
     * current 当前页码
     * size 每页条数
     * order 传驼峰格式
     * desc 若为true 从大到小排序
     */
    public ReturnPkg getArticleList(int current, int size, String order, boolean desc){
        Page<Article> pageParam = new Page<>(current,size);
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        if(desc){
            articleQueryWrapper
                    .orderByDesc(order)
                    .select("id","title","overview","time_stamp","user_name");
        }else{
            articleQueryWrapper
                    .orderByAsc(order)
                    .select("id","title","overview","time_stamp","user_name");
        }
        Page<Article> articlePage = articleMapper.selectPage(pageParam, articleQueryWrapper);
        return ReturnPkg.success(articlePage.getRecords());
    }

    public ReturnPkg getMyArticle(int current, int size, String order, boolean desc){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        Page<Article> pageParam = new Page<>(current,size);
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        if(desc){
            articleQueryWrapper
                    .eq("user_name", userName)
                    .orderByDesc(order)
                    .select("id","title","overview","time_stamp");
        }else{
            articleQueryWrapper
                    .eq("user_name", userName)
                    .orderByAsc(order)
                    .select("id","title","overview","time_stamp");
        }
        Page<Article> articlePage = articleMapper.selectPage(pageParam, articleQueryWrapper);
        return ReturnPkg.success(articlePage.getRecords());
    }

    public ReturnPkg getArticleDetail(int id){
        return ReturnPkg.success(articleMapper.selectById(id));
    }

    @Transactional
    public ReturnPkg createArticle(Article inputArticle){
        //设置作者名称
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        inputArticle.setUserName(userName);
        articleMapper.insert(inputArticle);

        User userByUserName = userService.getUserByUserName(userName);
        userByUserName.setArticleNum(userByUserName.getArticleNum()+1);
        userService.updateUser(userByUserName);
        return ReturnPkg.success();
    }

    @Transactional
    public ReturnPkg updateArticle(Article inputArticle){
        articleMapper.updateById(inputArticle);
        return ReturnPkg.success();
    }



    @Transactional
    public ReturnPkg deleteArticle(int id){
        articleMapper.deleteById(id);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User userByUserName = userService.getUserByUserName(userName);
        userByUserName.setArticleNum(userByUserName.getArticleNum()-1);
        userService.updateUser(userByUserName);
        return ReturnPkg.success();
    }

}
