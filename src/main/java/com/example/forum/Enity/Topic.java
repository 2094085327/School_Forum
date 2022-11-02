package com.example.forum.Enity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author zeng
 * @Date 2022/10/16 12:25
 * @User 86188
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("topic")
public class Topic {

    @TableId(type = IdType.AUTO)
    private int id;

    private String cId;

    private String title;

    private String content;

    private int pv;

    private int userId;

    private String username;

    private String userImg;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private int hot;

    private int deletes;

    private int deleteRole;

}
