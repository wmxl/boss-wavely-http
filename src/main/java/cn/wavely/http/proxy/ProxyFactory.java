package cn.wavely.http.proxy;

import cn.wavely.http.filter.FilterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.lang.reflect.Proxy;

/**
 * @author chenglong
 * @create 2022-06-20 20:33
 * @desc
 **/
public class ProxyFactory<T> implements FactoryBean<T> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private T bean;
    private Class<T> requestClass;
    private FilterFactory filterFactory;
    private DefaultListableBeanFactory beanFactory;

    public void setRequestClass(Class<T> requestClass) {
        this.requestClass = requestClass;
    }

    public void setFilterFactory(FilterFactory filterFactory) {
        this.filterFactory = filterFactory;
    }

    public void setBeanFactory(DefaultListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public T getObject() {
        logger.info("createObject:{}", requestClass.getName());
        if (bean == null) {
            synchronized (this) {
                if (bean == null) {
                    InterfaceProxyHandler handler = new InterfaceProxyHandler(requestClass, filterFactory, beanFactory);
                    bean = (T)Proxy.newProxyInstance(requestClass.getClassLoader(), new Class[]{requestClass}, handler);
                }
            }
        }
        return bean;
    }

    @Override
    public Class<T> getObjectType() {
        return requestClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
