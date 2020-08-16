package com.health.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @program:com.lucas.designpattern.fatory
 * @descrption: 支付工厂类
 *
 * @author:Lucas.li
 * @create: 2020-01-09 23:43
 */
@Component
public class PayFactory {

    /**
     * 将多个的支付实现注入到一个map当中，key为声明的名字，value为具体的支付实现
     */
    @Autowired
    private Map<String,PayService> payServiceMap;

    @Autowired
    private List<PayService> payServices;

    public PayService createPay(String type){
        return payServiceMap.get(type);
    }

}

