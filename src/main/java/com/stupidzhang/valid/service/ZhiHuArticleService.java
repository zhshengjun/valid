package com.stupidzhang.valid.service;

import cn.hutool.http.HttpUtil;
import com.stupidzhang.valid.mapper.ZhiHuArticleMapper;
import com.stupidzhang.valid.model.ZhiHuArticle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ZhiHuArticleService {

    @Autowired
    private ZhiHuArticleMapper zhiHuArticleMapper;

    private static final String GOOGLE_URL = "https://www.google.com/ping";

    public void commit() {
        List<ZhiHuArticle> zhiHuArticles = zhiHuArticleMapper.selectAll();
        Map<String, Object> params = new HashMap<>(2);
        for (ZhiHuArticle zhihuArticle : zhiHuArticles) {
            params.put("sitemap", zhihuArticle.getUrl());
            try {
                HttpUtil.get(GOOGLE_URL, params);
                log.warn("提交链接：{}", zhihuArticle.getUrl());
                Thread.sleep(1000);
            } catch (Exception exception) {
                log.error("提交链接失败：{}", zhihuArticle.getUrl());
            }
        }
    }

    public void valid() {

        // 获取文章中的好物链接

    }


}
