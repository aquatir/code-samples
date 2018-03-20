/**
 * There are multiple ways to load properties on fly
 * <ul>
 *     <li>One is using {@link org.springframework.context.support.ReloadableResourceBundleMessageSource}. This API is designed
 * for reading (presumably) internationalized values and not properties, but you can still make it work. See config
 * here {@link learn_to_code.springboot.propconfig.reloadable_message_source.ReloadableMessageSourceConfig} and
 * controller call here {@link learn_to_code.springboot.controller.HelloWorldController#getReloadableProperties()}</li>
 *     <li>Another way is using apache common config lib (Not shown here currently</li>
 *     <li>Using spring-cloud config server. You can start reading here: https://cloud.spring.io/spring-cloud-config/</li>
 *     <li>Your custom solution</li>
 * </ul>
 *
 */
package learn_to_code.springboot.propconfig;
