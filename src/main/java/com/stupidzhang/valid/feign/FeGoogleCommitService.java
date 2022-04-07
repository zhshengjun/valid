package com.stupidzhang.valid.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cps-google", url = "https://www.google.com")
public interface FeGoogleCommitService {

    @GetMapping(value = "/ping")
    void commit(@RequestParam("sitemap") String sitemap);
}
