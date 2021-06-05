package com.pingpukj.tags.webend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @GetMapping("tags/test")
    @ApiOperation("test")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Id", dataType = "String", paramType = "query", dataTypeClass = String.class)
    )
    public int test(@RequestParam(value = "Id") String Id) {
        System.out.println(Id);
        return 1;
    }

}
