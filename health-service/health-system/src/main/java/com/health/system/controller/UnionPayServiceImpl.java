package com.health.system.controller;

import org.springframework.stereotype.Service;

/**
 * @program:com.lucas.designpattern.service.impl
 * @descrption:银联支付实现类
 * @author:Lucas.li
 * @create: 2020-01-09 23:21
 */
@Service("UNIONPAY")
public class UnionPayServiceImpl implements PayService {
    @Override
    public String pay() {
       return "银联支付成功";
    }
}

