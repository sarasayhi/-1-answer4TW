/*
 * Copyright (C) 2017 Zhejiang BYCDAO Technology CO.,LTD.
 * All rights reserved.
 * Official Web Site: http://www.bycdao.com.
 * Developer Web Site: http://open.bycdao.com.
 */

package com.Marissa.demo.controller;

import com.Marissa.demo.common.RestMessage;
import com.Marissa.demo.domain.ReqEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 * @since:swagger-bootstrap-ui-demo 1.0
 * @author <a href="mailto:xiaoymin@foxmail.com">xiaoymin@foxmail.com</a> 
 * 2017/09/04 21:12
 */
@Api(value = "example",tags = "example")
@RestController
@RequestMapping("/api/example")
public class ExampleController {

    @PostMapping("/examp1")
    @ApiOperation(value = "ModelAttribute",notes = "ModelAttribute类型参数")
    public RestMessage reqbody1(ReqEntity reqEntity){
        return new RestMessage(reqEntity);
    }
}
