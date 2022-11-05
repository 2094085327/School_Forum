package com.example.forum.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forum.Enity.LeavingMsg;
import com.example.forum.Mapper.LeavingMsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description:
 * @Author zeng
 * @Date 2022/11/3 21:37
 * @User 86188
 */
@RestController
@CrossOrigin
public class LeavingMsgController {
    LeavingMsgMapper leavingMsgMapper;

    @Autowired
    public LeavingMsgController(LeavingMsgMapper leavingMsgMapper) {
        this.leavingMsgMapper = leavingMsgMapper;
    }

    /**
     * 插入新留言
     */
    @RequestMapping("/msg/insertMsg")
    @ResponseBody
    public void insertTopic(@RequestParam("msgContent") String msgContent) {
        LeavingMsg leavingMsg = new LeavingMsg();
        List<LeavingMsg> list = leavingMsgMapper.selectList(null);
        leavingMsg.setCreateTime(LocalDateTime.now());
        leavingMsg.setUser("游客" + 10000 + list.size());
        leavingMsg.setContent(msgContent);
        leavingMsgMapper.insert(leavingMsg);
    }

    /**
     * 分页查询主题
     *
     * @param pageNum 起始位置
     * @param size    每页尺寸
     * @return 返回查询到的数据
     */
    @GetMapping("/msg/findByPage")
    public IPage<LeavingMsg> findByPage(@RequestParam("pageNum") int pageNum, @RequestParam("size") int size) {

        Page<LeavingMsg> page = new Page<>(pageNum, size);
        return leavingMsgMapper.selectPage(page, null);
    }

}
