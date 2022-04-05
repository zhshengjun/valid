package com.stupidzhang.valid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class HealthController {

    private String status = "OFFLINE";

    @ResponseBody
    @RequestMapping("/health/check")
    public void check(HttpServletResponse response) {
        response.setStatus(200);
    }

    @ResponseBody
    @RequestMapping("/health/status")
    public void status(HttpServletResponse response) {
        if ("ONLINE".equals(status)) {
            response.setStatus(200);
        } else {
            response.setStatus(500);
        }
    }

    @ResponseBody
    @RequestMapping("/health/online")
    public void online(HttpServletResponse response) {
        status = "ONLINE";
        response.setStatus(200);
    }

    @ResponseBody
    @RequestMapping("/health/offline")
    public void offline(HttpServletResponse response) {
        status = "OFFLINE";
        response.setStatus(200);
    }
}
