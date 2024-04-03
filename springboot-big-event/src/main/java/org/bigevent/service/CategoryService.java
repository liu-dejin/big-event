package org.bigevent.service;

import org.bigevent.model.Category;

import java.util.List;

public interface CategoryService {

    int insert(Category category);

    Category findById(int categoryId);

    List<Category> selectCategoryList();

    int categoryUpdate(Category category);

    void categoryDeleta(Integer id);
}
