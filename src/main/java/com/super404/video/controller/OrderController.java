package com.super404.video.controller;

import com.super404.video.domain.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单接口
 */
@RestController
@RequestMapping("/user/api/v1/order")
public class OrderController {

    @GetMapping("add")
    public JsonData saveOrder(){
        return JsonData.buildSuccess("下单成功");
    }
}
