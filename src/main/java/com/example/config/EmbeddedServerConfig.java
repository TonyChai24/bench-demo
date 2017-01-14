package com.example.config;

import com.example.util.ReflectHelper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardThreadExecutor;
import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.ProtocolHandler;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.stereotype.Component;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * Created by patterncat on 2017-01-04.
 */
@Component
public class EmbeddedServerConfig implements EmbeddedServletContainerCustomizer {

    private static final Logger logger = LoggerFactory.getLogger(EmbeddedServerConfig.class);

    private Map<String,Object> attributes = new HashMap<>();

    private ProtocolHandler protocolHandler;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        if(container instanceof TomcatEmbeddedServletContainerFactory){
            TomcatEmbeddedServletContainerFactory factory = (TomcatEmbeddedServletContainerFactory)container;
//            factory.setProtocol("org.apache.coyote.http11.Http11Nio2Protocol");
            factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
                @Override
                public void customize(Connector connector) {
                    ProtocolHandler handler = connector.getProtocolHandler();
                    protocolHandler = handler;

                    logger.info("handler:{}",handler.getClass().getName());

                    //todo test
//                    connector.setAttribute("maxThreads",500);
//                    connector.setAttribute("minSpareThreads",200);
//                    connector.setAttribute("acceptCount",1000);
//                    connector.setAttribute("keepAliveTimeout",2000);
//                    connector.setAttribute("soTimeout",2000);
//                    connector.setAttribute("connectionTimeout",2000);
//                    connector.setAttribute("maxConnections",20000);

                    logger.info("====== tomcat protocol config start ======");
                    Http11NioProtocol http11NioProtocol = (Http11NioProtocol)handler;
                    Method[] methods = Http11NioProtocol.class.getMethods();
                    attributes = ReflectHelper.reflectGetProperty(methods,http11NioProtocol);

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

    public ThreadPoolExecutor getStandardThreadExecutor() {
        return (ThreadPoolExecutor) protocolHandler.getExecutor();
    }



    public void registerMxbean(){
        //register mbean
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName("stat:type=WorkerTheadStat");
            WorkerTheadMXBean stat = new WorkerTheadStat(getStandardThreadExecutor());
            mbs.registerMBean(stat, name);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        }
    }
}
