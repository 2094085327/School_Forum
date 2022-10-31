package com.example.forum.Enity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description: 实体类USER
 * @Author zeng
 * @Date 2022/10/16 11:43
 * @User 86188
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private int id;

    private String phone;

    private String pwd;

    private int sex;

    private String img;

    private LocalDateTime createTime;

    private int role;

    private String username;
}
