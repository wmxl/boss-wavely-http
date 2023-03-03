package cn.wavely.http.spring;

import cn.wavely.http.filter.FilterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author chenglong
 * @create 2022-07-26 10:44
 * @desc
 **/
public class BeanDefineRegister implements BeanFactoryPostProcessor, ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private FilterFactory filterFactory;
    private ApplicationContext applicationContext;

    public BeanDefineRegister(FilterFactory filterFactory) {
        this.filterFactory = filterFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanScanner scanner = new BeanScanner((BeanDefinitionRegistry) beanFactory, filterFactory);
        scanner.setResourceLoader(applicationContext);
        List<String> basePackageList = ScannerConfiguration.basePackageList;
        if (basePackageList.size() == 0) {
            basePackageList = AutoConfigurationPackages.get(beanFactory);
        }
        logger.info("basePackages {}", basePackageList);
        scanner.scan(StringUtils.toStringArray(basePackageList));
    }
}
