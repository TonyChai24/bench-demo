package com.example.controller;

import com.example.config.EmbeddedServerConfig;
import com.example.util.ReflectHelper;
import org.apache.catalina.core.StandardThreadExecutor;
import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by patterncat on 2017-01-04.
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    EmbeddedServerConfig embeddedServerConfig;

    @RequestMapping("/thread")
    public Object getThreadPoolStats() throws MalformedObjectNameException {
//        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
//        Set<ObjectInstance> instances = mbs.queryMBeans(new ObjectName("Tomcat:type=Executor,*"), null);
//        return instances;
        ThreadPoolExecutor executor = embeddedServerConfig.getStandardThreadExecutor();

        Method[] methods = ThreadPoolExecutor.class.getMethods();
        Map<String,Object> data = ReflectHelper.reflectGetProperty(methods,executor);
        return data;
    }

    @RequestMapping("/tomcat")
    public Map<String,Object> getServerConfigs(){
        return embeddedServerConfig.getAttributes();
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
