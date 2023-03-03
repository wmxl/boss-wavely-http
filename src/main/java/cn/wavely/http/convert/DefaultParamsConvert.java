package cn.wavely.http.convert;

import cn.wavely.http.enums.MethodType;
import cn.wavely.http.http.RequestParams;
import cn.wavely.http.utils.ParamsConvertUtil;

import java.lang.annotation.Annotation;

/**
 * @author chenglong
 * @create 2022-07-21 14:42
 * @desc
 **/
public class DefaultParamsConvert implements ParamsConvert {

    @Override
    public boolean support(Annotation a) {
        if (a == null) {
            return true;
        }
        return false;
    }

    @Override
    public void convert(MethodType methodType, String paramName, Object param, RequestParams requestParams) {
        switch (methodType) {
            case GET:
                ParamsConvertUtil.fillMap(param, paramName, requestParams.getRequestParam());
                break;
            case POST:
                requestParams.setRequestBody(param);
                break;
            case PUT:
                requestParams.setRequestBody(param);
                break;
            case DELETE:
                requestParams.setRequestBody(param);
                break;
        }
    }
}
