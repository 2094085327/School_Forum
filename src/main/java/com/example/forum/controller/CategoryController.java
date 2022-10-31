package com.example.forum.controller;

import com.example.forum.Enity.Category;
import com.example.forum.Enity.User;
import com.example.forum.Mapper.CategoryMapper;
import com.example.forum.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author zeng
 * @Date 2022/10/16 16:06
 * @User 86188
 */
@RestController
public class CategoryController {
    CategoryMapper categoryMapper;
    @Autowired
    public CategoryController(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("/category/list")
    public List<Category> find(){
        return categoryMapper.selectList(null);
    }
}
