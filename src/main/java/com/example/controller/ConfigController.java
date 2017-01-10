package com.example.controller;

import com.example.config.EmbeddedTomcatConfig;
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
    EmbeddedTomcatConfig embeddedTomcatConfig;

    @RequestMapping("/tomcat")
    public Map<String,Object> getServerConfigs(){
        return embeddedTomcatConfig.getAttributes();
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
