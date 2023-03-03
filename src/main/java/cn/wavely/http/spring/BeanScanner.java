package cn.wavely.http.spring;

import cn.wavely.http.annotation.WavelyRequest;
import cn.wavely.http.filter.FilterFactory;
import cn.wavely.http.proxy.ProxyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

/**
 * @author chenglong
 * @create 2022-07-26 10:46
 * @desc
 **/
public class BeanScanner extends ClassPathBeanDefinitionScanner{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private FilterFactory filterFactory;

    public BeanScanner(BeanDefinitionRegistry registry, FilterFactory filterFactory) {
        super(registry);
        this.filterFactory = filterFactory;
    }

    public void registerDefaultFilters() {
        this.addIncludeFilter(new AnnotationTypeFilter(WavelyRequest.class));
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        beanDefinitionHolders.forEach(holder -> {
            GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();

            definition.getPropertyValues().add("filterFactory", filterFactory);
            try {
                definition.getPropertyValues().add("requestClass", Class.forName(definition.getBeanClassName()));
            } catch (ClassNotFoundException e) {
            }
            definition.getPropertyValues().add("beanFactory", getRegistry());
            definition.setBeanClass(ProxyFactory.class);
        });
        return beanDefinitionHolders;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().hasAnnotation(WavelyRequest.class.getName());
    }
}
