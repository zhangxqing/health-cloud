/*
 * @(#)DonateController.java 2019年12月20日 下午2:32:25
 * Copyright 2019 zmr, Inc. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.health.system.controller;

import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.R;
import com.health.system.domain.Donate;
import com.health.system.service.IDonateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>File：DonateController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2019 2019年12月20日 下午2:32:25</p>
 * <p>Company: zmrit.com </p>
 *
 * @author zmr
 * @version 1.0
 */
@RestController
@RequestMapping("donate")
public class DonateController extends BaseController {
    @Autowired
    private IDonateService donateService;

    @GetMapping("list")
    public R list(Donate donate) {
        startPage();
        List<Donate> list = donateService.selectDistrictsList(donate);
        return result(list);
    }
}
