package com.example.forum.Mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.forum.Enity.MoreContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description:
 * @Author zeng
 * @Date 2022/10/27 12:19
 * @User 86188
 */
@Mapper
public interface MoreContentMapper extends BaseMapper<MoreContent> {
    /**
     * 多表联合查询
     *
     * @param wrapper 查询时传来的条件
     * @return 返回List
     */
    @Select("SELECT topic.*, reply.user_id AS 'replyId',reply.username AS 'replyName',reply.content AS 'replyContent',reply.floor,reply.create_time AS 'replyTime'  FROM topic LEFT JOIN reply ON topic.id = reply.topic_id  ${ew.customSqlSegment}")
    List<MoreContent> selectReplyTitle(@Param(Constants.WRAPPER) QueryWrapper<MoreContent> wrapper);

    /**
     * 多表联合查询
     *
     * @param page    分页参数
     * @param wrapper 查询时传来的条件
     * @return 返回page
     */
    @Select("SELECT topic.*, reply.user_id AS 'replyId',reply.username AS 'replyName',reply.content AS 'replyContent',reply.floor,reply.create_time AS 'replyTime'  FROM topic LEFT JOIN reply ON topic.id = reply.topic_id  ${ew.customSqlSegment}")
    <P extends IPage<MoreContent>> P selectReplyTitlePage(P page, @Param(Constants.WRAPPER) QueryWrapper<MoreContent> wrapper);


}
