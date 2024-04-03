package org.bigevent.service;

import org.bigevent.model.Article;
import org.bigevent.model.PageBean;

public interface ArticleService {
    int insert(Article article);

    Article findById(int artId);

    PageBean<Article> selectArticleList(Integer pageNum, Integer pageSize, String categoryId, String state);

    void delete(Integer id);

    int update(Article article);
}
