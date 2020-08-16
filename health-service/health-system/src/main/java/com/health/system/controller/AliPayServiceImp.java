package com.health.system.controller;

import org.springframework.stereotype.Service;

/**
 * @program:com.lucas.designpattern.service.impl
 * @descrption:支付宝支付类
 * @author:Lucas.li
 * @create: 2020-01-09 23:19
 */
@Service("ALIPAY")
public class AliPayServiceImp implements PayService {
    @Override
    public String pay() {
        return "支付宝支付成功";
    }
}

