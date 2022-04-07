package com.stupidzhang.valid.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stupidzhang.valid.feign.FeCpsApiService;
import com.stupidzhang.valid.feign.FeGoogleCommitService;
import com.stupidzhang.valid.feign.FeZhiHuLinkService;
import com.stupidzhang.valid.mapper.CpsInvalidItemMapper;
import com.stupidzhang.valid.mapper.ZhiHuArticleMapper;
import com.stupidzhang.valid.model.CpsInvalidItem;
import com.stupidzhang.valid.model.ZhiHuArticle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ZhiHuArticleService {

    @Autowired
    private ZhiHuArticleMapper zhiHuArticleMapper;

    @Autowired
    private CpsInvalidItemMapper cpsInvalidItemMapper;

    @Autowired
    private FeGoogleCommitService feGoogleCommitService;

    @Autowired
    private FeZhiHuLinkService feZhiHuLinkService;

    @Autowired
    private FeCpsApiService feCpsApiService;


    public void commit() {
        List<ZhiHuArticle> zhiHuArticles = zhiHuArticleMapper.selectAll();
        for (ZhiHuArticle zhihuArticle : zhiHuArticles) {
            try {
                feGoogleCommitService.commit(zhihuArticle.getUrl());
                log.warn("提交链接：{}", zhihuArticle.getUrl());
                Thread.sleep(1000);
            } catch (Exception exception) {
                log.error("提交链接失败：{}", zhihuArticle.getUrl());
            }
        }
    }

    public void valid() {
        // 获取文章中的好物链接
        List<ZhiHuArticle> zhiHuArticles = zhiHuArticleMapper.selectAll();
        for (ZhiHuArticle zhiHuArticle : zhiHuArticles) {
            log.info("解析 {} 的好物链接", zhiHuArticle.getTitle());
            validContent(zhiHuArticle);
        }
    }

    private void validContent(ZhiHuArticle zhiHuArticle) {
        String token = StringUtils.isEmpty(zhiHuArticle.getAnswerId()) ? zhiHuArticle.getArticleId() : zhiHuArticle.getAnswerId();
        JSONObject requestResult = feZhiHuLinkService.parse(zhiHuArticle.getType(), token);
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) requestResult.get("data");
        List<CpsInvalidItem> list = new ArrayList<>();
        for (Object value : map.values()) {
            list.add(validUrl(value, zhiHuArticle.getId()));
        }
        list = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
        if (!list.isEmpty()) {
            cpsInvalidItemMapper.batchInsert(list);
        }
    }

    private CpsInvalidItem validUrl(Object value, Integer articleId) {
        JSONObject goods = JSONObject.parseObject(JSON.toJSONString(value)).getJSONObject("goods");
        JSONObject goods_url = goods.getJSONObject("goods_url");
        String url = goods_url.getString("url");
        Boolean data = feCpsApiService.valid(url).getData();
        String sku_id = goods.getString("sku_id");
        log.info("{}，解析结果：{}", sku_id, data);
        if (Boolean.TRUE.equals(data) || cpsInvalidItemMapper.count(sku_id) > 0) {
            return null;
        }
        return CpsInvalidItem.builder().title(goods.getString("title")).goodsId(goods.getString("sku_id")).goodsUrl(url).platform(goods.getString("subtitle")).articleId(articleId).build();
    }


}
