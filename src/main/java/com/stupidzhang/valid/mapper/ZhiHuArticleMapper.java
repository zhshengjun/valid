package com.stupidzhang.valid.mapper;

import com.stupidzhang.valid.model.ZhiHuArticle;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZhiHuArticleMapper {


    ZhiHuArticle selectByPrimaryKey(Long id);

    List<ZhiHuArticle> selectAll();
}