package com.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by patterncat on 2017-01-04.
 */
@Component
public class EmbeddedServerConfig implements EmbeddedServletContainerCustomizer {

    private static final Logger logger = LoggerFactory.getLogger(EmbeddedServerConfig.class);

    private Map<String,Object> attributes = new HashMap<>();

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        if(container instanceof UndertowEmbeddedServletContainerFactory){

        }
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
