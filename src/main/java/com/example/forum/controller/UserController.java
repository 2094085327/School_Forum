package com.example.forum.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forum.Enity.User;
import com.example.forum.Mapper.UserMapper;
import com.example.forum.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @Description:
 * @Author zeng
 * @Date 2022/10/16 15:52
 * @User 86188
 */
@RestController
@CrossOrigin
public class UserController {
    UserMapper userMapper;

    @Autowired
    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
        find();
    }

    /**
     * 放在CDN上的随机头像
     */
    private static final String[] HEAD_IMG = {
            "static/pic/1.jpg",
            "static/pic/2.jpg",
            "static/pic/3.jpg",
            "static/pic/4.jpg",
            "static/pic/5.jpg",
            "static/pic/6.jpg",
            "static/pic/7.jpg",
            "static/pic/8.jpg",
            "static/pic/9.jpg",
            "static/pic/10.jpg",
            "static/pic/11.jpg",
            "static/pic/12.jpg",
            "static/pic/13.jpg",
            "static/pic/14.jpg",
            "static/pic/15.jpg",
    };

    private String getRandomImg() {
        int size = HEAD_IMG.length;
        Random random = new Random();
        int index = random.nextInt(size);
        return HEAD_IMG[index];
    }

    /**
     * 查询全部
     *
     * @return 全部列表
     */
    @GetMapping("/user/list")
    public List<User> find() {
        System.out.println("数据库连接测试");
        System.out.println(userMapper.selectList(null));
        return userMapper.selectList(null);

    }

    @GetMapping("/user/findByPage")
    public IPage<User> findByPage() {
        Page<User> page = new Page<>(0, 5);
        return userMapper.selectPage(page, null);
    }

    @GetMapping("/user/findByName")
    public User findByName(@RequestParam("userName") String userName) {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.eq("username", userName);
        return userMapper.selectOne(queryWrapper);
    }

    @GetMapping("/user/findById")
    public User findById(@RequestParam("userId") int userId) {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.eq("id", userId);
        return userMapper.selectOne(queryWrapper);
    }

    /**
     * 注册新用户
     *
     * @param phoneNumber  电话号码
     * @param password     密码
     * @param makeUserName 用户名
     */
    @RequestMapping("/user/insert")
    @ResponseBody
    public void insertUser(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("password") String password, @RequestParam("makeUserName") String makeUserName) {
        User user = new User();
        user.setPhone(phoneNumber);
        user.setSex(1);
        user.setImg(getRandomImg());
        user.setRole(1);
        user.setUsername(makeUserName);
        user.setPwd(CommonUtil.mD5(password));
        user.setCreateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @RequestMapping("/user/login")
    @ResponseBody
    public User loginIn(@RequestParam("password") String password, @RequestParam("makeUserName") String userName) {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.eq("username", userName);
        User user = userMapper.selectOne(queryWrapper);
        // 当数据存在时进行初始化
        if (user != null) {
            if (user.getPwd().equals(CommonUtil.mD5(password))) {
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @GetMapping("/user/updateById")
    public User updateById(@RequestParam("userId") int uerId, @RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd, @RequestParam("userName") String userName, @RequestParam("userPhone") String userPhone, @RequestParam("userSex") String userSex) {
        UpdateWrapper<User> queryWrapper = new UpdateWrapper<>();
        queryWrapper.eq("id", uerId);
        User user = userMapper.selectOne(queryWrapper);
        if (!Objects.equals(user.getPwd(), CommonUtil.mD5(oldPwd))) {
            System.out.println(user.getPwd());
            System.out.println(user.getPwd());
            System.out.println("密码错误");
            return null;
        } else {
            if ("男".equals(userSex)) {
                userSex = "1";
            }
            if ("女".equals((userSex))) {
                userSex = "0";
            }
            queryWrapper.eq("id", uerId).set("username", userName).set("phone", userPhone).set("pwd", CommonUtil.mD5(newPwd)).set("sex", Integer.parseInt(userSex));
            userMapper.update(null, queryWrapper);
            return user;
        }
    }
}
