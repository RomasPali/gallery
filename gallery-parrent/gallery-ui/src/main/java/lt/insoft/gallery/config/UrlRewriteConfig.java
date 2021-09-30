package lt.insoft.gallery.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.DispatcherType;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

@Configuration
public class UrlRewriteConfig {

    @Bean
    public FilterRegistrationBean urlRewriteFilter() {
        Map<String, String> params = new HashMap<>();
        params.put("logLevel", "slf4j");

        FilterRegistrationBean reg = new FilterRegistrationBean();
        reg.setFilter(new UrlRewriteFilter());
        reg.addUrlPatterns("/*");
        reg.setInitParameters(params);
        reg.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR);
        return reg;
    }
}
