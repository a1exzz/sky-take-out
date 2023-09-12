package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import org.springframework.context.annotation.Bean;

public interface CategoryService {
    /**
     * 修改分类
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * 新增分类
     * @param categoryDTO
     */
    void insert(CategoryDTO categoryDTO);

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 分类菜品启用禁用
     * @param status
     * @param id
     */
    void startOrStop(Integer status, long id);

    /**
     * 根据id删除菜品
     * @param id
     */
    void deleteById(long id);

    /**
     * 通过类型查询分类
     * @param type
     * @return
     */
    Category selectByType(Integer type);
}
