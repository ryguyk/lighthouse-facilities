package gov.va.api.lighthouse.facilities;

import gov.va.api.lighthouse.talos.PathRewriteFilter;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * Configuration for doing path re-writes. Removes the leading path(load balancer) from the request.
 */
@Slf4j
@Configuration
public class PathRewriteConfig {

  static List<String> leadingPaths() {
    return List.of("/va_facilities/", "/services/va_facilities/", "/facilities/");
  }

  @Bean
  FilterRegistrationBean<PathRewriteFilter> pathRewriteFilter() {
    var registration = new FilterRegistrationBean<PathRewriteFilter>();
    PathRewriteFilter filter =
        PathRewriteFilter.builder().removeLeadingPath(leadingPaths()).build();
    registration.setFilter(filter);
    registration.setOrder(Ordered.LOWEST_PRECEDENCE);
    registration.addUrlPatterns(filter.removeLeadingPathsAsUrlPatterns());
    log.info("Enabling path rewriting for: {}", leadingPaths());
    return registration;
  }
}
