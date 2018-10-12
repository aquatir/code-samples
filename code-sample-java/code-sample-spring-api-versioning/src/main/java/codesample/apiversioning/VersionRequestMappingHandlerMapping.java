package codesample.apiversioning;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

public class VersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        VersionRange methodAnnotation = AnnotationUtils.findAnnotation(
                method, VersionRange.class);
        return createCondition(methodAnnotation);
    }

    private RequestCondition<?> createCondition(VersionRange accessMapping) {
        return (accessMapping != null) ?
                new VersionRequestCondition(accessMapping.value()) :
                null;
    }
}
