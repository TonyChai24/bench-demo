package com.example.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by cat on 2017-01-14.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jacksonMessageConverter());
        super.configureMessageConverters(converters);
    }

    private MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS,
                        SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)
                .featuresToEnable(SerializationFeature.INDENT_OUTPUT);
        // can use this instead of featuresToEnable(...)
        builder.indentOutput(true);
        return new MappingJackson2HttpMessageConverter(builder.build());
    }
}
