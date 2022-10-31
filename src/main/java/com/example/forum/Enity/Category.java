package com.example.forum.Enity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Author zeng
 * @Date 2022/10/16 11:53
 * @User 86188
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("category")
public class Category {
    private int id;

    private String name;

    private int weight;

    private Date createTime;
}
