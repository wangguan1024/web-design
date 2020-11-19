package com.hhuc.webdesign;

import com.hhuc.webdesign.mapper.ArticleMapper;
import com.hhuc.webdesign.entity.Article;
import com.hhuc.webdesign.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebDesignApplicationTests {

    @Autowired
    ArticleMapper articleMapper;

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
