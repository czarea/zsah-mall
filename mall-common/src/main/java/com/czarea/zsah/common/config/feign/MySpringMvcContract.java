package com.czarea.zsah.common.config.feign;

import com.czarea.zsah.common.annotation.ApiVersion;
import com.czarea.zsah.common.annotation.UrlVersion;
import com.czarea.zsah.common.util.StringPool;
import com.czarea.zsah.common.util.StringUtil;
import feign.MethodMetadata;
import feign.Util;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 多分版本处理
 *
 * @author zhouzx
 */
public class MySpringMvcContract extends SpringMvcContract {

    public MySpringMvcContract(List<AnnotatedParameterProcessor> annotatedParameterProcessors, ConversionService conversionService) {
        super(annotatedParameterProcessors, conversionService);
    }

    @Override
    protected void processAnnotationOnMethod(MethodMetadata data, Annotation methodAnnotation, Method method) {
        if (RequestMapping.class.isInstance(methodAnnotation) || methodAnnotation.annotationType()
            .isAnnotationPresent(RequestMapping.class)) {
            Class<?> targetType = method.getDeclaringClass();
            // url 上的版本，优先获取方法上的版本
            UrlVersion urlVersion = AnnotatedElementUtils.findMergedAnnotation(method, UrlVersion.class);

            // 注意：在父类之前 添加 url版本，在父类之后，处理 Media Types 版本
            super.processAnnotationOnMethod(data, methodAnnotation, method);
            String path = "";
            RequestMapping methodMapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);
            if (methodMapping.value().length > 0) {
                path = Util.emptyToNull(methodMapping.value()[0]);
            }

            // 再次尝试类上的版本
            if (urlVersion == null || urlVersion.value() != 1) {
                urlVersion = AnnotatedElementUtils.findMergedAnnotation(targetType, UrlVersion.class);
            }
            if (urlVersion != null && urlVersion.value() != 1) {
                String versionUrl = "/" + urlVersion.value();
                data.template().uri(path + "/" + urlVersion.value());
            }

            // 处理 Media Types 版本信息
            ApiVersion apiVersion = AnnotatedElementUtils.findMergedAnnotation(method, ApiVersion.class);
            // 再次尝试类上的版本
            if (apiVersion == null || StringUtil.isBlank(apiVersion.value())) {
                apiVersion = AnnotatedElementUtils.findMergedAnnotation(targetType, ApiVersion.class);
            }
            if (apiVersion != null && StringUtil.isNotBlank(apiVersion.value())) {
                data.template().header("version", apiVersion.value());
            }
        }
    }

    /**
     * 参考：https://gist.github.com/rmfish/0ed59a9af6c05157be2a60c9acea2a10
     *
     * @param annotations 注解
     * @param paramIndex 参数索引
     * @return 是否 http 注解
     */
    @Override
    protected boolean processAnnotationsOnParameter(MethodMetadata data, Annotation[] annotations, int paramIndex) {
        boolean httpAnnotation = super.processAnnotationsOnParameter(data, annotations, paramIndex);
        // 在 springMvc 中如果是 Get 请求且参数中是对象 没有声明为@RequestBody 则默认为 Param
        if (!httpAnnotation && StringPool.GET.equals(data.template().method().toUpperCase())) {
            for (Annotation parameterAnnotation : annotations) {
                if (!(parameterAnnotation instanceof RequestBody)) {
                    return false;
                }
            }
            data.queryMapIndex(paramIndex);
            return true;
        }
        return httpAnnotation;
    }
}
