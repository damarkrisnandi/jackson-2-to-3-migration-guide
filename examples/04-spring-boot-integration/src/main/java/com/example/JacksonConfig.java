package com.example;

import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tools.jackson.databind.cfg.DateTimeFeature;

@Configuration
public class JacksonConfig {

    /**
     * Jackson 2: Jackson2ObjectMapperBuilderCustomizer (customises Jackson2ObjectMapperBuilder).
     * Jackson 3: JsonMapperBuilderCustomizer (customises JsonMapper.Builder).
     * Do not declare your own ObjectMapper bean just to tweak settings; Boot's JsonMapper bean keeps its defaults.
     */
    @Bean
    public JsonMapperBuilderCustomizer jacksonCustomizer() {
        return builder -> builder.disable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
