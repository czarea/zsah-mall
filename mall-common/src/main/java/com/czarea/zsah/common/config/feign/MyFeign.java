package com.czarea.zsah.common.config.feign;

import feign.Contract;
import feign.Feign;
import feign.InvocationHandlerFactory;
import feign.Target;
import feign.hystrix.FallbackFactory;
import feign.hystrix.HystrixDelegatingContract;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ReflectionUtils;

/**
 * @author zhouzx
 */
@SuppressWarnings("ALL")
public class MyFeign {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends Feign.Builder
        implements ApplicationContextAware {

        private Contract contract = new Contract.Default();

        private ApplicationContext applicationContext;

        private FeignContext feignContext;

        @Override
        public Feign.Builder invocationHandlerFactory(
            InvocationHandlerFactory invocationHandlerFactory) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Builder contract(Contract contract) {
            this.contract = contract;
            return this;
        }

        @Override
        public Feign build() {
            super.invocationHandlerFactory(new InvocationHandlerFactory() {
                @Override
                public InvocationHandler create(Target target,
                    Map<Method, MethodHandler> dispatch) {
                    // using reflect get fallback and fallbackFactory properties from
                    // FeignClientFactoryBean because FeignClientFactoryBean is a package
                    // level class, we can not use it in our package
                    Object feignClientFactoryBean = Builder.this.applicationContext
                        .getBean("&" + target.type().getName());

                    Class fallback = (Class) getFieldValue(feignClientFactoryBean,
                        "fallback");
                    Class fallbackFactory = (Class) getFieldValue(feignClientFactoryBean,
                        "fallbackFactory");
                    String name = (String) getFieldValue(feignClientFactoryBean, "name");

                    Object fallbackInstance;
                    FallbackFactory fallbackFactoryInstance;
                    MyFallbackFactory funFallbackFactory = new MyFallbackFactory(target);
                    if (void.class != fallback) {
                        fallbackInstance = getFromContext(name, "fallback", fallback,
                            target.type());
                        return new MyFeignInvocationHandler(target, dispatch,
                            new FallbackFactory.Default(fallbackInstance));
                    }
                    if (void.class != fallbackFactory) {
                        fallbackFactoryInstance = (FallbackFactory) getFromContext(name,
                            "fallbackFactory", fallbackFactory,
                            FallbackFactory.class);
                        return new MyFeignInvocationHandler(target, dispatch,
                            fallbackFactoryInstance);
                    }
                    return new MyFeignInvocationHandler(target, dispatch,funFallbackFactory);
                }

                private Object getFromContext(String name, String type,
                    Class fallbackType, Class targetType) {
                    Object fallbackInstance = feignContext.getInstance(name,
                        fallbackType);
                    if (fallbackInstance == null) {
                        throw new IllegalStateException(String.format(
                            "No %s instance of type %s found for feign client %s",
                            type, fallbackType, name));
                    }

                    if (!targetType.isAssignableFrom(fallbackType)) {
                        throw new IllegalStateException(String.format(
                            "Incompatible %s instance. Fallback/fallbackFactory of type %s is not assignable to %s for feign client %s",
                            type, fallbackType, targetType, name));
                    }
                    return fallbackInstance;
                }
            });

            super.contract(new HystrixDelegatingContract(contract));
            return super.build();
        }

        private Object getFieldValue(Object instance, String fieldName) {
            Field field = ReflectionUtils.findField(instance.getClass(), fieldName);
            field.setAccessible(true);
            try {
                return field.get(instance);
            } catch (IllegalAccessException e) {
                // ignore
            }
            return null;
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
            this.applicationContext = applicationContext;
            feignContext = this.applicationContext.getBean(FeignContext.class);
        }
    }
}
