package cn.wavely.http.config;

import cn.wavely.http.filter.Filter;
import cn.wavely.http.filter.FilterFactory;
import cn.wavely.http.filter.impl.RequestExecuteFilter;
import cn.wavely.http.http.RequestFactory;
import cn.wavely.http.http.okhttp.OkHttpRequestFactory;
import cn.wavely.http.spring.BeanDefineRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenglong
 * @create 2022-07-25 14:22
 * @desc
 **/
public class AutoConfiguration {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ConfigurationProperties(prefix = "http")
    @Bean
    public HttpConfiguration httpConfiguration() {
        return new HttpConfiguration();
    }

    @Bean
    public RequestFactory requestFactory(HttpConfiguration httpConfiguration) {
        return new OkHttpRequestFactory(httpConfiguration);
    }

    @Bean
    public FilterFactory filterFactory(RequestFactory requestFactory) {
        RequestExecuteFilter requestExecuteFilter = new RequestExecuteFilter(requestFactory);
        List<Filter> filterList = new ArrayList<>();
        filterList.add(requestExecuteFilter);

        FilterFactory filterFactory = new FilterFactory(filterList);
        return filterFactory;
    }

    @Bean
    public BeanDefineRegister beanDefineRegister(FilterFactory filterFactory) {
        return new BeanDefineRegister(filterFactory);
    }
}
