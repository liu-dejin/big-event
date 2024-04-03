package org.bigevent.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.bigevent.mapper.ArticleMapper;
import org.bigevent.model.Article;
import org.bigevent.model.PageBean;
import org.bigevent.service.ArticleService;
import org.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Article findById(int artId) {
        return articleMapper.selectByPrimaryKey((long) artId);
    }

    @Override
    public PageBean<Article> selectArticleList(Integer pageNum, Integer pageSize, String categoryId, String state) {
        // 1，创建分页对象
        PageBean<Article> pageBean = new PageBean<>();
        // 2，开启分页查询
        PageHelper.startPage(pageNum, pageSize);
        // 3，执行查询
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("userId");
        List<Article> articleList = articleMapper.selectArticleList(userId, categoryId, state);
        // 4，封装分页对象
        Page<Article> page = (Page<Article>) articleList;
        // 5，设置分页对象属性
        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());
        return pageBean;
    }

    @Override
    public void delete(Integer id) {
        articleMapper.deleteByPrimaryKey((long) id);
    }

    @Override
    public int update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.updateByPrimaryKeySelective(article);
        return article.getId();
    }

    @Override
    public int insert(Article article) {
        Map<String, Object> map = ThreadLocalUtil.get();
        article.setCreateUser((Integer) map.get("userId"));
        article.setUpdateTime(LocalDateTime.now());
        article.setCreateTime(LocalDateTime.now());
        articleMapper.insertSelective(article);
        return article.getId();
    }

}
