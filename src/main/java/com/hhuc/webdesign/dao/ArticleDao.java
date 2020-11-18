package com.hhuc.webdesign.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhuc.webdesign.entity.Article;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDao extends BaseMapper<Article> {
}
