package com.hhuc.webdesign.controller;

import com.hhuc.webdesign.entity.Article;
import com.hhuc.webdesign.service.ArticleService;
import com.hhuc.webdesign.util.ReturnPkg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @GetMapping("/article/current/{current}/size/{size}/order/{order}/desc/{desc}")
    public ReturnPkg getArticleList(
            @PathVariable("current") Integer current,
            @PathVariable("size") Integer size,
            @PathVariable("order") String order,
            @PathVariable("desc") Boolean desc
    ) {
        return articleService.getArticleList(current, size, order, desc);
    }

    @GetMapping("/my/article/current/{current}/size/{size}/order/{order}/desc/{desc}")
    public ReturnPkg getMyArticle(
            @PathVariable("current") Integer current,
            @PathVariable("size") Integer size,
            @PathVariable("order") String order,
            @PathVariable("desc") Boolean desc
    ) {
        return articleService.getMyArticle(current, size, order, desc);
    }

    @GetMapping("/article/detail/id/{id}")
    public ReturnPkg getArticleDetail(@PathVariable("id") Integer id){
        return articleService.getArticleDetail(id);
    }


    @PostMapping("/my/article")
    public ReturnPkg createArticle(@RequestBody Article article){
        return articleService.createArticle(article);
    }

    @PutMapping("/my/article")
    public  ReturnPkg updateArticle(@RequestBody Article article){
        return articleService.updateArticle(article);
    }

    @DeleteMapping("/my/article/id/{id}")
    public ReturnPkg deleteArticle(@PathVariable Integer id){
        return articleService.deleteArticle(id);
    }
}
