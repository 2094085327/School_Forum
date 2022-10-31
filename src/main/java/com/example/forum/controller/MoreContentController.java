package com.example.forum.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forum.Enity.MoreContent;
import com.example.forum.Mapper.MoreContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author zeng
 * @Date 2022/10/29 20:21
 * @User 86188
 */
@RestController
@CrossOrigin
public class MoreContentController {
    MoreContentMapper moreContentMapper;

    @Autowired
    public MoreContentController(MoreContentMapper moreContentMapper) {
        this.moreContentMapper = moreContentMapper;
    }


    /**
     * 多表查询文章标题和回复内容
     *
     * @param pageNum 查询起始位置
     * @param size    每页尺寸
     * @param replyId 回复者id
     * @param deletes 是否删除
     * @return 返回查询后的数据
     */
    @GetMapping("/more/topicAndReplyPage")
    public IPage<MoreContent> topicAndReplyPage(@RequestParam("pageNum") int pageNum, @RequestParam("size") int size, @RequestParam("replyId") String replyId, @RequestParam("deletes") Integer deletes) {
        Page<MoreContent> page = new Page<>(pageNum, size);
        QueryWrapper<MoreContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("reply.deletes", deletes).eq("reply.user_id", replyId);
        return moreContentMapper.selectReplyTitlePage(page, queryWrapper);
    }
}
