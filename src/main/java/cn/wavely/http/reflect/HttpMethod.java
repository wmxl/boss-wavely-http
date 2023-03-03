package cn.wavely.http.reflect;

import cn.wavely.http.annotation.WavelyRequest;
import cn.wavely.http.convert.ParamsConvertFactory;
import cn.wavely.http.enums.MethodType;
import cn.wavely.http.exception.HttpException;
import cn.wavely.http.filter.FilterChain;
import cn.wavely.http.filter.RequestContext;
import cn.wavely.http.http.RequestParams;
import cn.wavely.http.http.Response;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenglong
 * @create 2022-06-21 15:44
 * @desc
 **/
public class HttpMethod {
    private HttpMethodConfig config;
    private Class returnClass;
    private MethodType methodType;
    private List<String> paramNames;
    private Map<String, Annotation> paramAnnotationMap;
    private FilterChain filterChain;

    public HttpMethod(WavelyRequest classConfig, WavelyRequest methodConfig, Method method, FilterChain filterChain, DefaultListableBeanFactory beanFactory) {
        config = new HttpMethodConfig(classConfig, methodConfig, beanFactory);
        returnClass = method.getReturnType();
        methodType = MethodType.getByHttpAnnotation(method.getAnnotations());
        this.filterChain = filterChain;

        Parameter[] parameters = method.getParameters();
        if (parameters.length > 0) {
            paramNames = new ArrayList<>();
            paramAnnotationMap = new HashMap<>();
            for (Parameter parameter: parameters) {
                paramNames.add(parameter.getName());
                Annotation[] annotations = parameter.getAnnotations();
                if (annotations.length > 0) {
                    paramAnnotationMap.put(parameter.getName(), annotations[0]);
                }
            }
        }
    }

    public Object execute(Object[] args) {
        RequestParams params = getParams(args);
        RequestContext requestContext = new RequestContext();
        requestContext.setRequestParams(params);
        requestContext.setRetry(config.getRetry());

        filterChain.filter(requestContext);

        Response response = requestContext.getResponse();
        if (!response.isSuccess()) {
            throw new HttpException(response.errMsg());
        }
        return response.body();
    }

    private RequestParams getParams(Object[] args) {
        RequestParams params = new RequestParams();
        params.setUrl(config.getUrl());
        params.setReturnClass(returnClass);
        params.setMethodType(methodType);

        Map<String, String> headers = new HashMap<>(config.getHeaders());
        params.setHeaders(headers);
        Map<String, String> requestParams = new HashMap<>(config.getRequestParams());
        params.setRequestParam(requestParams);

        for (int i = 0; i < args.length; i ++) {
            Object param = args[i];
            String paramName = paramNames.get(i);
            Annotation annotation = paramAnnotationMap.get(paramName);
            ParamsConvertFactory.getInstance().convert(annotation, methodType, paramName, param, params);
        }
        return params;
    }
}
