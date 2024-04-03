package org.bigevent.controller;

import lombok.extern.slf4j.Slf4j;
import org.bigevent.model.Article;
import org.bigevent.model.PageBean;
import org.bigevent.service.ArticleService;
import org.bigevent.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 添加文章
     *
     * @param article
     * @return
     */
    @PostMapping
    public Result<Article> insert(@RequestBody @Validated(Article.insert.class) Article article) {
        int artId = articleService.insert(article);
        Article articleData = articleService.findById(artId);
        return Result.success(articleData);
    }

    /**
     * 更新文章
     *
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @param state
     * @return
     */
    @GetMapping
    public Result<PageBean<Article>> articleList(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> articlePageBean = articleService.selectArticleList(pageNum, pageSize, categoryId, state);
        return Result.success(articlePageBean);
    }

    /**
     * 更新文章
     *
     * @param article
     * @return
     */
    @PutMapping
    public Result<Article> update(@RequestBody @Validated(Article.update.class) Article article) {
        int articleId = articleService.update(article);
        Article articleData = articleService.findById(articleId);
        return Result.success(articleData);
    }

    /**
     * 文章详情
     *
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Result detail(Integer id) {
        Article article = articleService.findById(id);
        if (article == null) return Result.error("文章不存在");
        return Result.success(article);
    }

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public Result delete(Integer id) {
        Article article = articleService.findById(id);
        if (article == null) return Result.error("文章不存在");
        articleService.delete(id);
        return Result.success("删除成功");
    }
}
