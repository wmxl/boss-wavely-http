package cn.wavely.http.proxy;

import cn.wavely.http.annotation.WavelyRequest;
import cn.wavely.http.exception.AnnoationNotFoundException;
import cn.wavely.http.filter.Filter;
import cn.wavely.http.filter.FilterChain;
import cn.wavely.http.filter.FilterFactory;
import cn.wavely.http.reflect.HttpMethod;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author chenglong
 * @create 2022-06-20 20:45
 * @desc
 **/
public class InterfaceProxyHandler implements InvocationHandler {
    private Map<Method, HttpMethod> methodMap = new HashMap<Method, HttpMethod>();
    private Class interfaceClass;

    public InterfaceProxyHandler(Class interfaceClass, FilterFactory filterFactory, DefaultListableBeanFactory beanFactory) {
        WavelyRequest classConfig = (WavelyRequest) interfaceClass.getAnnotation(WavelyRequest.class);
        if (classConfig == null) {
            throw new AnnoationNotFoundException(interfaceClass.getName(), classConfig.toString());
        }
        this.interfaceClass = interfaceClass;
        List<Filter> classFilterList = getFilters(classConfig, filterFactory);
        Method[] methods = interfaceClass.getDeclaredMethods();
        for (Method method : methods) {
            WavelyRequest methodConfig = AnnotatedElementUtils.getMergedAnnotation(method, WavelyRequest.class);
            if (methodConfig == null) {
                continue;
            }
            List<Filter> filterList = filterFactory.getBaseFilterList();
            filterList.addAll(classFilterList);
            filterList.addAll(getFilters(methodConfig, filterFactory));
            filterList.sort((a, b) -> {
                if (a.order() < b.order()) {
                    return 1;
                }
                return -1;
            });

            HttpMethod httpMethod = new HttpMethod(classConfig, methodConfig, method, new FilterChain(filterList), beanFactory);
            methodMap.put(method, httpMethod);
        }
    }

    private List<Filter> getFilters(WavelyRequest request, FilterFactory filterFactory) {
        Class<Filter>[] filterClasses = request.filters();
        if (filterClasses.length < 1) {
            return Collections.emptyList();
        }
        List<Filter> filterList = new ArrayList<>();
        for (int i = 0; i < filterClasses.length; i++) {
            filterList.add(filterFactory.getFilter(filterClasses[i]));
        }
        return filterList;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        HttpMethod httpMethod = methodMap.get(method);
        if (httpMethod != null) {
            return httpMethod.execute(args);
        } else {
            String methodName = method.getName();
            if (methodName.equals("toString")) {
                return this.toString();
            } else if (methodName.equals("hashCode")) {
                return interfaceClass.hashCode() * 13 + this.hashCode();
            } else if (methodName.equals("equals")) {
                return args[0] == proxy;
            } else if (methodName.equals("clone")) {
                throw new CloneNotSupportedException("clone is not supported for jade dao.");
            } else {
                throw new UnsupportedOperationException(interfaceClass.getName() + "#" + method.getName());
            }
        }
    }
}
