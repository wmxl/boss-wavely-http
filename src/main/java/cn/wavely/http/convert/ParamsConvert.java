package cn.wavely.http.convert;

import cn.wavely.http.enums.MethodType;
import cn.wavely.http.http.RequestParams;

import java.lang.annotation.Annotation;

/**
 * @author chenglong
 * @create 2022-07-20 17:25
 * @desc
 **/
public interface ParamsConvert {

    /**
     * 支持注解转换
     * @param a
     * @return
     */
    boolean support(Annotation a);

    /**
     * 进行转换
     * @param methodType
     * @param paramName
     * @param param
     * @param requestParams
     */
    void convert(MethodType methodType, String paramName, Object param, RequestParams requestParams);
}
