package org.bigevent.service.impl;

import org.bigevent.mapper.CategoryMapper;
import org.bigevent.model.Category;
import org.bigevent.service.CategoryService;
import org.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据id查询分类
     *
     * @param categoryId
     * @return
     */
    @Override
    public Category findById(int categoryId) {
        return categoryMapper.selectByPrimaryKey((long) categoryId);
    }

    /**
     * 查询分类列表
     *
     * @return
     */
    @Override
    public List<Category> selectCategoryList() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("userId");
        return categoryMapper.selectCategoryList(userId);
    }

    /**
     * 修改分类
     *
     * @param category
     * @return
     */
    @Override
    public int categoryUpdate(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.updateByPrimaryKeySelective(category);
        return category.getId();
    }

    @Override
    public void categoryDeleta(Integer id) {
        categoryMapper.deleteByPrimaryKey((long) id);
    }

    /**
     * 新增分类
     *
     * @param category
     * @return
     */
    @Override
    public int insert(Category category) {
        Map<String, Object> map = ThreadLocalUtil.get();
        category.setCreateUser((Integer) map.get("userId"));
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateTime(LocalDateTime.now());
        categoryMapper.insertSelective(category);
        return category.getId();
    }

}
