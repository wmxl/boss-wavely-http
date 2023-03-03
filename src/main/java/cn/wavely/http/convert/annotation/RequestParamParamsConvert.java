package cn.wavely.http.convert.annotation;

import cn.wavely.http.annotation.RequestParam;
import cn.wavely.http.convert.ParamsConvert;
import cn.wavely.http.enums.MethodType;
import cn.wavely.http.http.RequestParams;
import cn.wavely.http.utils.ParamsConvertUtil;

import java.lang.annotation.Annotation;

/**
 * @author chenglong
 * @create 2022-07-20 17:55
 * @desc
 **/
public class RequestParamParamsConvert implements ParamsConvert {

    @Override
    public boolean support(Annotation a) {
        if (a != null && a instanceof RequestParam) {
            return true;
        }
        return false;
    }

    @Override
    public void convert(MethodType methodType, String paramName, Object param, RequestParams requestParams) {
        ParamsConvertUtil.fillMap(param, paramName, requestParams.getRequestParam());
    }
}
