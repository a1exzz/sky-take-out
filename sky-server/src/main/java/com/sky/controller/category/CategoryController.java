package com.sky.controller.category;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *菜品管理
 */
@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 修改分类
     * @param categoryDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改分类")
    public Result update(@RequestBody CategoryDTO categoryDTO){//从前端以json 格式获取数据需要加上@RequestBody
        log.info("分类参数{}",categoryDTO);
        categoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     * 新增分类
     * @param categoryDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增分类")
  public  Result save(@RequestBody CategoryDTO categoryDTO){
        log.info("分类信息{}",categoryDTO);
        categoryService.insert(categoryDTO);
        return Result.success();
  }

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
  @GetMapping("/page")
  @ApiOperation("分类分页查询")
    public Result<PageResult> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分类分页{}",categoryPageQueryDTO);
      PageResult pageResult=categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);

  }

    /**
     * 菜品分类 启用禁用
     * @param status
     * @param id
     * @return
     */
  @PostMapping("/status/{status}")
  @ApiOperation("菜品分类启用禁用")
  public  Result startOrStop(@PathVariable Integer status ,long id){
      log.info("账号信息{},{}",status,id);
      categoryService.startOrStop(status,id);
      return Result.success();
  }

    /**
     * 根据id删除菜品
     * @param id
     * @return
     */
  @DeleteMapping
  @ApiOperation("根据id删除菜品分类")
  public Result deleteById(Long id){
      categoryService.deleteById(id);
      return Result.success();
  }

    /**
     * 通过类型查询分类
     * @param type
     * @return
     */

  @GetMapping("/list")
  @ApiOperation("通过类型查询分类")
  public Result<Category> selectByType(Integer type){
      Category category=categoryService.selectByType(type);
      return Result.success(category);

  }
}
