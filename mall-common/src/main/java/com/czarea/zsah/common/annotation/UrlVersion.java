package com.czarea.zsah.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解用于生成 requestMappingInfo 时候直接拼接路径规则，自动放置于方法路径开始部分
 *
 * @author zhouzx
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface UrlVersion {

    /**
     * url 路径中的版本
     *
     * @return 版本号
     */
    int value() default 1;
}
