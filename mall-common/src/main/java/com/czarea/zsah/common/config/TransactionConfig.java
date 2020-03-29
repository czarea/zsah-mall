package com.czarea.zsah.common.config;

import java.util.Properties;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 事务配置
 *
 * @author zhouzx
 */
@Configuration
@ConditionalOnWebApplication(type = Type.SERVLET)
public class TransactionConfig {

    @Bean(name = "txAdvice")
    public TransactionInterceptor txAdvisor(DataSourceTransactionManager dataSourceTransactionManager) {
        Properties properties = new Properties();
        String propagation = "PROPAGATION_REQUIRED,-Exception";
        properties.setProperty("create*", propagation);
        properties.setProperty("add*", propagation);
        properties.setProperty("save*", propagation);
        properties.setProperty("update*", propagation);
        properties.setProperty("delete*", propagation);
        properties.setProperty("modify*", propagation);
        return new TransactionInterceptor(dataSourceTransactionManager, properties);
    }

    @Bean
    public BeanNameAutoProxyCreator txProxy() {
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setInterceptorNames("txAdvice");
        creator.setBeanNames("*Service", "*ServiceImpl");
        creator.setProxyTargetClass(true);
        return creator;
    }
}
