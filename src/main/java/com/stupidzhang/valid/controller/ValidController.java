package com.stupidzhang.valid.controller;

import com.stupidzhang.valid.service.ZhiHuArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidController {

    @Autowired
    private ZhiHuArticleService zhiHuArticleService;

    @GetMapping("/commit")
    public void commit() {
        zhiHuArticleService.commit();
    }

    @GetMapping("/valid")
    public void valid() {
        zhiHuArticleService.valid();
    }
}
