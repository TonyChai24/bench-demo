package com.example.config;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
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
        if(container instanceof JettyEmbeddedServletContainerFactory){
            JettyEmbeddedServletContainerFactory factory = (JettyEmbeddedServletContainerFactory)container;
            factory.addServerCustomizers(new JettyServerCustomizer() {
                @Override
                public void customize(final Server server) {
                    // Tweak the connection pool used by Jetty to handle incoming HTTP connections
                    final QueuedThreadPool threadPool = server.getBean(QueuedThreadPool.class);
                    threadPool.setMaxThreads(200);
//                    threadPool.setMinThreads(Integer.valueOf(minThreads));
//                    threadPool.setIdleTimeout(Integer.valueOf(idleTimeout));
                }
            });
        }
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
