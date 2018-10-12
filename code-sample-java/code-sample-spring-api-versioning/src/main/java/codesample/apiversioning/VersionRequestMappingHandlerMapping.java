package codesample.apiversioning;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

public class VersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        VersionRange versionRangeAnnotation = AnnotationUtils.findAnnotation(method, VersionRange.class);

        if (versionRangeAnnotation != null)
            return new VersionRequestCondition(versionRangeAnnotation.value());
        else
            return null;
    }
}
