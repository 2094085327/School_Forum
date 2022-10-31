package com.example.forum.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forum.Enity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Author zeng
 * @Date 2022/10/16 11:46
 * @User 86188
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
