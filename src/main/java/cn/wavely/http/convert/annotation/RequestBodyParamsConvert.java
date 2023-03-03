package cn.wavely.http.convert.annotation;

import cn.wavely.http.annotation.RequestBody;
import cn.wavely.http.convert.ParamsConvert;
import cn.wavely.http.enums.MethodType;
import cn.wavely.http.http.RequestParams;

import java.lang.annotation.Annotation;

/**
 * @author chenglong
 * @create 2022-07-21 14:40
 * @desc
 **/
public class RequestBodyParamsConvert implements ParamsConvert {

    @Override
    public boolean support(Annotation a) {
        if (a != null && a instanceof RequestBody) {
            return true;
        }
        return false;
    }

    @Override
    public void convert(MethodType methodType, String paramName, Object param, RequestParams requestParams) {
        requestParams.setRequestBody(param);
    }
}
