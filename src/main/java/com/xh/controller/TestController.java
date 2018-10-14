package com.xh.controller;

import com.xh.service.ITestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaohe
 * @version V1.0.0
 * @Description:
 * @date: 2018-10-14 15:24
 * @Copyright:
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ITestServices testServiceImpl;

    // http://127.0.0.1:8083/mds/test/query
    @GetMapping("/query")
    public String query(){
        this.testServiceImpl.queryDocuments();
        this.testServiceImpl.queryUsers();
        return "";
    }

}
