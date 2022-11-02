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
 * @Date 2022/10/16 12:14
 * @User 86188
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("reply")
public class Reply {
    @TableId(type = IdType.AUTO)
    private int id;

    private int topicId;

    private int floor;

    private String content;

    private int userId;

    private String username;

    private String userImg;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private int deletes;

    private int delete_role;
}
