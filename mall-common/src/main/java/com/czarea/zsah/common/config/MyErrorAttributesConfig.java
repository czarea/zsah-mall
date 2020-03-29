package com.czarea.zsah.common.config;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequest;

/**
 * 自定义DefaultErrorAttributes
 *
 * @author zhouzx
 */
@Configuration
@ConditionalOnClass(ServletRequest.class)
public class MyErrorAttributesConfig {

    private static final Logger logger = LoggerFactory.getLogger(MyErrorAttributes.class);


    @Bean
    public DefaultErrorAttributes defaultErrorAttributes() {
        return new MyErrorAttributes();
    }

    private static class MyErrorAttributes extends DefaultErrorAttributes {

        @Override
        public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
            Map<String, Object> attrs = super.getErrorAttributes(webRequest, includeStackTrace);
            logger.error("error occur,message is {}", attrs.toString());
            Map<String, Object> response = new HashMap<>(2);
            response.put("code", attrs.get("status"));
            response.put("msg", attrs.get("message"));
            return response;
        }

    }
}
