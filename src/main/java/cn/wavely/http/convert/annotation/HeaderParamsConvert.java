package cn.wavely.http.convert.annotation;

import cn.wavely.http.annotation.Header;
import cn.wavely.http.convert.ParamsConvert;
import cn.wavely.http.enums.MethodType;
import cn.wavely.http.http.RequestParams;
import cn.wavely.http.utils.ParamsConvertUtil;

import java.lang.annotation.Annotation;

/**
 * @author chenglong
 * @create 2022-07-21 14:38
 * @desc
 **/
public class HeaderParamsConvert implements ParamsConvert {

    @Override
    public boolean support(Annotation a) {
        if (a != null && a instanceof Header) {
            return true;
        }
        return false;
    }

    @Override
    public void convert(MethodType methodType, String paramName, Object param, RequestParams requestParams) {
        ParamsConvertUtil.fillMap(param, paramName, requestParams.getHeaders());
    }
}
