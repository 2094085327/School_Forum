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
 * @Date 2022/11/3 21:33
 * @User 86188
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("leavmsg")
public class LeavingMsg {
    @TableId(type = IdType.AUTO)
    private int id;

    private String content;

    private String user;

    private LocalDateTime createTime;
}
