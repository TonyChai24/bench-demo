package com.example.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cat on 2017-01-14.
 */
public class ReflectHelper {

    private static final Logger logger = LoggerFactory.getLogger(ReflectHelper.class);

    public static Map<String,Object> reflectGetProperty(Method[] methods,Object instance){
        Map<String,Object> attributes = new HashMap<>();
        for(Method m:methods){
            if(!m.getName().startsWith("get")){
                continue;
            }
            Class<?> methodParams[] = m.getParameterTypes();
            if(methodParams == null || methodParams.length == 0){
                Object value = null;
                try {
                    value = m.invoke(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String prop = StringUtils.uncapitalize(m.getName().substring(3));
                logger.info("property:{} value:{}",prop,value);
                attributes.put(prop,value);
            }
        }
        return attributes;
    }
}
