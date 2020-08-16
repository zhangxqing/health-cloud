package com.health.system.controller;

import org.springframework.stereotype.Service;


/**
 * @program:com.lucas.designpattern.service.impl
 * @descrption:微信支付类
 * @author:Lucas.li
 * @create: 2020-01-09 23:18
 */
@Service("WECHATPAY")
public class WechatPayServiceImpl implements PayService {
    @Override
    public String pay() {
        return "微信支付成功";
    }
}

