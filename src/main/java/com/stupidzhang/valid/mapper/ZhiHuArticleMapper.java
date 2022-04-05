package com.stupidzhang.valid.mapper;

import com.stupidzhang.valid.model.ZhiHuArticle;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZhiHuArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ZhiHuArticle record);

    int insertSelective(ZhiHuArticle record);

    ZhiHuArticle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ZhiHuArticle record);

    int updateByPrimaryKey(ZhiHuArticle record);

    List<ZhiHuArticle> selectAll();
}