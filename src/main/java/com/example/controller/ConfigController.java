package com.example.controller;

import com.example.config.EmbeddedServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by patterncat on 2017-01-04.
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    EmbeddedServerConfig embeddedServerConfig;

    @RequestMapping("/tomcat")
    public Map<String,Object> getServerConfigs(){
        return embeddedServerConfig.getAttributes();
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
