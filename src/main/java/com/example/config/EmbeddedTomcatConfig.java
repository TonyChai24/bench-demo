package com.example.config;

import org.apache.catalina.connector.Connector;
import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.ProtocolHandler;
import org.apache.coyote.http11.Http11NioProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by patterncat on 2017-01-04.
 */
@Component
public class EmbeddedTomcatConfig implements EmbeddedServletContainerCustomizer {

    private static final Logger logger = LoggerFactory.getLogger(EmbeddedTomcatConfig.class);

    private Map<String,Object> attributes = new HashMap<>();

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        if(container instanceof TomcatEmbeddedServletContainerFactory){
            TomcatEmbeddedServletContainerFactory factory = (TomcatEmbeddedServletContainerFactory)container;
            factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
                @Override
                public void customize(Connector connector) {
                    ProtocolHandler handler = connector.getProtocolHandler();
                    logger.info("handler:{}",handler.getClass().getName());

                    //todo test
//                    connector.setAttribute("maxThreads",1);
//                    connector.setAttribute("acceptCount",2);

                    logger.info("====== tomcat protocol config start ======");
                    Http11NioProtocol http11NioProtocol = (Http11NioProtocol)handler;
                    Method[] methods = Http11NioProtocol.class.getMethods();
                    for(Method m:methods){
                        if(!m.getName().startsWith("get")){
                            continue;
                        }
                        Class<?> methodParams[] = m.getParameterTypes();
                        if(methodParams == null || methodParams.length == 0){
                            Object value = null;
                            try {
                                value = m.invoke(http11NioProtocol);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            String prop = StringUtils.uncapitalize(m.getName().substring(3));
                            logger.info("property:{} value:{}",prop,value);
                            attributes.put(prop,value);
                        }
                    }
                    logger.info("====== tomcat protocol config end ======");

//                    Object defaultMaxThreads = connector.getAttribute("maxThreads");
//                    Object acceptCount = connector.getAttribute("acceptCount");
//
//                    logger.info("default maxThreads:{}",defaultMaxThreads);
//                    logger.info("default acceptCount:{}",acceptCount);
                }
            });
        }
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
