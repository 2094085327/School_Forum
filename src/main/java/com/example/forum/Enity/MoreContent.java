package com.example.forum.Enity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author zeng
 * @Date 2022/10/27 12:19
 * @User 86188
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("reply")
public class MoreContent extends Reply {
    private int id;

    private String title;

    private int floor;

    private String replyId;

    private String replyName;

    private String replyContent;

   private LocalDateTime replyTime;

}
