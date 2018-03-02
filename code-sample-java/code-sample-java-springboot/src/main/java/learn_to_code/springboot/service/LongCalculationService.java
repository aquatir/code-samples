package learn_to_code.springboot.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * You can configure caches in spring-boot transparently (docs: https://docs.spring.io/autorepo/docs/spring-boot/1.5.10.RELEASE/reference/html/boot-features-caching.html)
 * In order to do it, some steps are required:
 * <ul>
 *     <li>Allow caches in your app with @EnableCaching annotations ({@link learn_to_code.springboot.SpringBootRunner}</li>
 *     <li>Add @Cacheable("cache_name") annotation to method on which you want to cache result</li>
 * </ul>
 *
 * Autoconfigured cache will use concurrent map which is not exactly recommend for production (it may work in some cases through)
 * You should read documentation of your cache provided before configuring it as it may or may not have some features crucial
 * for your production environment.
 */
@Service
public class LongCalculationService {

    private static final double START_NUMBER = 100.0;

    @Cacheable("result")
    public double compute(int n) {
        double initial = START_NUMBER;
        for (int i = 0; i < n; i++) {
            initial = Math.atan(initial);
        }
        return initial;
    }
}
