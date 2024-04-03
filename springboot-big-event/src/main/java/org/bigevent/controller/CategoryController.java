package org.bigevent.controller;

import lombok.extern.slf4j.Slf4j;
import org.bigevent.model.Category;
import org.bigevent.service.CategoryService;
import org.bigevent.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增文章分类
     *
     * @param category
     * @return
     */
    @PostMapping
    public Result<Category> insert(@RequestBody @Validated(Category.insert.class) Category category) {
        int categoryId = categoryService.insert(category);
        Category categoryData = categoryService.findById(categoryId);
        return Result.success(categoryData);
    }

    /**
     * 查询文章分类列表
     *
     * @return
     */
    @GetMapping
    public Result<List<Category>> getCategory() {
        List<Category> categoryList = categoryService.selectCategoryList();
        return Result.success(categoryList);
    }

    /**
     * 根据id查询文章分类
     *
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Result CategoryDetail(Integer id) {
        Category byId = categoryService.findById(id);
        if (byId == null){
            return Result.error("分类不存在");
        }
        return Result.success(byId);
    }

    /**
     * 根据id修改文章分类
     * @param category
     * @return
     */
    @PutMapping()
    public Result<Category> CategoryUpdate(@RequestBody @Validated(Category.update.class) Category category){
        int byId = categoryService.categoryUpdate(category);
        Category categoryData = categoryService.findById(byId);
        return Result.success(categoryData);
    }

    /**
     * 根据id删除文章分类
     * @param id
     * @return
     */
    @DeleteMapping
    public Result CategoryDelete(Integer id){
        Category byId = categoryService.findById(id);
        if (byId == null){
            return Result.error("分类不存在");
        }
        categoryService.categoryDeleta(id);
        return Result.success();
    }
}
