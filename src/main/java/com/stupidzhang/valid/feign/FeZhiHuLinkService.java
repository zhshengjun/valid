package com.stupidzhang.valid.feign;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.shaded.com.google.gson.JsonObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cps-zhihu", url = "https://www.zhihu.com")
public interface FeZhiHuLinkService {

    @GetMapping(value = "/api/v4/mcn/v2/linkcards")
    JSONObject parse(@RequestParam("content_type") String contentType, @RequestParam("token") String token);
}
