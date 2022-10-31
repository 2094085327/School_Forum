package com.example.forum.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forum.Enity.Reply;
import com.example.forum.Enity.User;
import com.example.forum.Mapper.ReplyMapper;
import com.example.forum.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description:
 * @Author zeng
 * @Date 2022/10/16 17:54
 * @User 86188
 */
@RestController
@CrossOrigin
public class ReplyController {
    ReplyMapper replyMapper;
    UserMapper userMapper;

    @Autowired
    public ReplyController(ReplyMapper replyMapper, UserMapper userMapper) {
        this.replyMapper = replyMapper;
        this.userMapper = userMapper;
    }

    /**
     * 测试，显示全部回复
     *
     * @return 返回reply表中全部回复
     */
    @GetMapping("/reply/list")
    public List<Reply> list() {
        return replyMapper.selectList(null);
    }

    /**
     * 分页查询回复
     *
     * @param pageNum 开始相
     * @param size    每页尺寸
     * @param topicId 根据主题id进行查询
     * @param deletes 判断是否被删除
     * @return 返回查询后的结果
     */
    @GetMapping("/reply/findByPage")
    public IPage<Reply> findByPage(@RequestParam("pageNum") int pageNum, @RequestParam("size") int size, @RequestParam("topicId") String topicId, @RequestParam("deletes") int deletes) {
        String nulls = "null";
        Page<Reply> page = new Page<>(pageNum, size);
        QueryWrapper<Reply> queryWrapper = Wrappers.query();

        if (nulls.equals(topicId)) {
            // 不查询文章ID
            queryWrapper.eq("deletes", deletes);
        } else {
            // 查询文章ID
            queryWrapper.eq("topic_id", topicId).eq("deletes", deletes);
        }
        return replyMapper.selectPage(page, queryWrapper);
    }

    /**
     * 分页查询回复
     *
     * @param pageNum 开始相
     * @param size    每页尺寸
     * @param userId  根据用户id进行查询
     * @param deletes 判断是否被删除
     * @return 返回查询后的结果
     */
    @GetMapping("/reply/findByUserId")
    public IPage<Reply> findByUserId(@RequestParam("pageNum") int pageNum, @RequestParam("size") int size, @RequestParam("deletes") int deletes, @RequestParam("userId") String userId) {
        Page<Reply> page = new Page<>(pageNum, size);
        QueryWrapper<Reply> queryWrapper = Wrappers.query();
        queryWrapper.eq("user_id", userId).eq("deletes", deletes);
        return replyMapper.selectPage(page, queryWrapper);
    }

    /**
     * 插入新回复
     *
     * @param topicId      回复的文章ID
     * @param topicContent 文章回复内容
     * @param userId       用户id
     */
    @RequestMapping("/reply/insertReply")
    @ResponseBody
    public void insertTopic(@RequestParam("topicId") int topicId, @RequestParam("topicContent") String topicContent, @RequestParam("userId") int userId) {
        Reply reply = new Reply();
        reply.setTopicId(topicId);

        QueryWrapper<Reply> queryWrapper = Wrappers.query();
        queryWrapper.like("topic_id", topicId);

        replyMapper.selectList(queryWrapper);

        reply.setFloor(replyMapper.selectList(queryWrapper).size() + 1);
        reply.setContent(topicContent);
        reply.setUserId(userId);

        QueryWrapper<User> queryWrapper2 = Wrappers.query();
        queryWrapper2.eq("id", userId);
        User user = userMapper.selectOne(queryWrapper2);

        reply.setUsername(user.getUsername());
        reply.setUserImg(user.getImg());
        reply.setCreateTime(LocalDateTime.now());
        reply.setUpdateTime(LocalDateTime.now());
        reply.setDeletes(0);

        replyMapper.insert(reply);
    }

    /**
     * 更新回复的状态
     *
     * @param replyId 回复ID
     * @param deletes 是否删除
     */
    @GetMapping("/reply/updateReply")
    public void updateReply(@RequestParam("replyId") String replyId, @RequestParam("deletes") Integer deletes) {
        UpdateWrapper<Reply> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", replyId).set("deletes", deletes);
        replyMapper.update(null, updateWrapper);
    }
}