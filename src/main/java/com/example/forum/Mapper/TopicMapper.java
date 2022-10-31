package com.example.forum.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forum.Enity.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description:
 * @Author zeng
 * @Date 2022/10/16 12:26
 * @User 86188
 */
@Mapper
public interface TopicMapper extends BaseMapper<Topic> {

}
