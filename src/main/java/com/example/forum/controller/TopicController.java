package com.example.forum.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forum.Enity.Topic;
import com.example.forum.Enity.User;
import com.example.forum.Mapper.TopicMapper;
import com.example.forum.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description:
 * @Author zeng
 * @Date 2022/10/16 16:57
 * @User 86188
 */
@RestController
@CrossOrigin
public class TopicController {
    TopicMapper topicMapper;
    UserMapper userMapper;

    @Autowired
    public TopicController(TopicMapper topicMapper, UserMapper userMapper) {
        this.topicMapper = topicMapper;
        this.userMapper = userMapper;
    }

    /**
     * 测试
     *
     * @return 返回全部主题
     */
    @GetMapping("/topic/list")
    public List<Topic> find() {
        QueryWrapper<Topic> queryWrapper = Wrappers.query();
        queryWrapper.like("deletes", 0);
        return topicMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询主题
     *
     * @param pageNum 起始位置
     * @param size    每页尺寸
     * @param cId     根据文章类型进行查询
     * @param deletes 是否删除
     * @return 返回查询到的数据
     */
    @GetMapping("/topic/findByPage")
    public IPage<Topic> findByPage(@RequestParam("pageNum") int pageNum, @RequestParam("size") int size, @RequestParam("cId") String cId, @RequestParam("deletes") int deletes) {

        Page<Topic> page = new Page<>(pageNum, size);
        String nulls = "全部";
        QueryWrapper<Topic> queryWrapper = Wrappers.query();
        if (nulls.equals(cId)) {
            queryWrapper.eq("deletes", deletes);
            queryWrapper.orderByDesc("hot");
            queryWrapper.orderByAsc("create_time");
        } else {
            queryWrapper.eq("c_id", cId).eq("deletes", deletes);
        }
        return topicMapper.selectPage(page, queryWrapper);
    }

    /**
     * 分页查询主题
     *
     * @param pageNum 起始位置
     * @param size    每页尺寸
     * @param userId  用户ID
     * @param cId     根据文章类型进行查询
     * @param deletes 是否删除
     * @return 返回查询到的数据
     */
    @RequestMapping("/topic/findByUserId")
    @ResponseBody
    public IPage<Topic> findByUserId(@RequestParam("userId") String userId, @RequestParam("pageNum") int pageNum, @RequestParam("size") int size, @RequestParam("cId") String cId, @RequestParam("deletes") int deletes, @RequestParam("deleteRole") int deleteRole) {
        Page<Topic> page = new Page<>(pageNum, size);
        String nulls = "全部";
        QueryWrapper<Topic> queryWrapper = Wrappers.query();
        if (nulls.equals(cId)) {
            queryWrapper.eq("user_id", userId).eq("deletes", deletes);
            queryWrapper.orderByDesc("create_time").eq("delete_role", deleteRole);
        } else {
            queryWrapper.eq("user_id", userId).eq("c_id", cId).eq("deletes", deletes).eq("delete_role", deleteRole);
        }
        return topicMapper.selectPage(page, queryWrapper);
    }

    /**
     * 根据文章id进行查询
     *
     * @param topicId 文章ID
     * @param deletes 是否删除
     * @return 返回数据
     */
    @GetMapping("/topic/findById")
    public List<Topic> findById(@RequestParam("topicId") String topicId, @RequestParam("deletes") int deletes, @RequestParam int deleteRole) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", topicId).eq("deletes", deletes).eq("delete_role", deleteRole);
        return topicMapper.selectList(queryWrapper);
    }

    /**
     * 插入主题
     *
     * @param topicTitle   要插入的主题标题
     * @param topicContent 内容
     * @param userId       用户
     * @param cId          文章类型
     */
    @RequestMapping("/topic/insertTopic")
    @ResponseBody
    public void insertTopic(@RequestParam("topicTitle") String topicTitle, @RequestParam("topicContent") String topicContent, @RequestParam("userId") String userId, @RequestParam("cId") String cId) {

        Topic topic = new Topic();
        topic.setCId(cId);
        topic.setTitle(topicTitle);
        topic.setContent(topicContent);
        topic.setPv(1);
        topic.setUserId(Integer.parseInt(userId));

        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.eq("id", userId);
        User user = userMapper.selectOne(queryWrapper);

        topic.setUsername(user.getUsername());
        topic.setUserImg(user.getImg());
        topic.setCreateTime(LocalDateTime.now());
        topic.setUpdateTime(LocalDateTime.now());
        topic.setHot(0);
        topic.setDeletes(0);
        topic.setDeleteRole(1);
        topicMapper.insert(topic);
    }

    /**
     * 更新主题是否被删除
     *
     * @param topicId 文章ID
     * @param deletes 删除状态
     */
    @GetMapping("/topic/updateTopic")
    public void updateTopic(@RequestParam("topicId") String topicId, @RequestParam("deletes") int deletes, @RequestParam("deleteRole") int deleteRole) {
        UpdateWrapper<Topic> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", topicId).set("deletes", deletes).set("delete_role", deleteRole);
        topicMapper.update(null, updateWrapper);
    }

    /**
     * 更新文章热度
     *
     * @param topicId 文章ID
     */
    @GetMapping("/topic/updateHot")
    public void updateHot(@RequestParam("topicId") String topicId) {
        Topic topic;
        UpdateWrapper<Topic> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", topicId);
        topic = topicMapper.selectOne(updateWrapper);
        updateWrapper.eq("id", topicId).set("hot", topic.getHot() + 1);
        topicMapper.update(null, updateWrapper);
    }
}
