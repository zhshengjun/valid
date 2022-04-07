package com.stupidzhang.valid.feign;

import com.stupidzhang.valid.model.base.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cps-server")
public interface FeCpsApiService {

    @GetMapping(value = "/cps/valid")
    Result<Boolean> valid(@RequestParam("content") String content);
}
