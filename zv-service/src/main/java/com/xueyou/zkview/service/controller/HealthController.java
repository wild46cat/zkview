package com.xueyou.zkview.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wuxueyou on 2017/6/11.
 */
@RestController
@RequestMapping("/health")
public class HealthController {
    @RequestMapping("/test")
    public String test(){
        return "ok";
    }
}
