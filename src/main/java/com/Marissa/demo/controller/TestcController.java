/*
 * Copyright (C) 2017 Zhejiang BYCDAO Technology CO.,LTD.
 * All rights reserved.
 * Official Web Site: http://www.bycdao.com.
 * Developer Web Site: http://open.bycdao.com.
 */

package com.Marissa.demo.controller;

import com.Marissa.demo.common.Rest;
import com.Marissa.demo.common.RestMessage;
import com.Marissa.demo.domain.ReqEntity;
import com.Marissa.demo.domain.RestEetity;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/***
 *
 * @since:swagger-bootstrap-ui-demo 1.0
 * @author <a href="mailto:xiaoymin@foxmail.com">xiaoymin@foxmail.com</a> 
 * 2017/08/10 14:47
 */
//@Api(value = "测试C",tags = "测试C")
//@RequestMapping("/api/testc")
//@RestController
public class TestcController {

//    @PostMapping("/selectLog")
//    @ApiOperation(value = "selectLog",notes = "selectLog")
    public RestMessage selectLog(@RequestBody Map<String,Object> infoMap,
                                 @RequestAttribute(name = "CURRENTUSERID" ) int userId){
        return new RestMessage(infoMap);
    }

//    @PostMapping("/select1")
//    @ApiOperation(value = "header参数",notes = "header参数")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "code",name = "code",dataType = "string"),
            @ApiImplicitParam(value = "headerparam",name = "headerparam",dataType = "string"),
            @ApiImplicitParam(value = "page",name = "page",dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "page1",name = "page1",dataType = "Long",paramType = "query")
    })
    public RestMessage reqbody3(@RequestHeader(value = "headerparam") String headerparam, @RequestParam(value = "code") String code,
                                @RequestParam(value = "page") int page, @RequestParam(value = "page1") Long page1){
        return new RestMessage(ImmutableMap.of("code",code,"header",headerparam,"page",page,"page1",page1));
    }

//    @DeleteMapping(value = "/xdf/{code}")
//    @ApiImplicitParams({@ApiImplicitParam(value = "code",name = "code",dataType = "Long",paramType = "path")})
    public RestMessage urlpath(@PathVariable(value = "code") Long code){
        return new RestMessage(code);
    }
//    @GetMapping("/rest")
    public Rest<ReqEntity> rest(){
        ReqEntity reqEntity=new ReqEntity();
        reqEntity.setName("张飞");
        reqEntity.setTel("13093939102");
       Rest<ReqEntity> restEetity=new Rest<>();
       restEetity.setData(reqEntity);
        return restEetity;
    }

//    @GetMapping("/resp")
    public RestEetity resp(){
        ReqEntity reqEntity=new ReqEntity();
        reqEntity.setName("张飞");
        reqEntity.setTel("13093939102");
        RestEetity restEetity=new RestEetity();
        restEetity.setReqEntity(reqEntity);
        return restEetity;
    }
}
