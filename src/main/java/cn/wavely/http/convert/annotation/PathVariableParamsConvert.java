package cn.wavely.http.convert.annotation;

import cn.wavely.http.annotation.PathVariable;
import cn.wavely.http.convert.ParamsConvert;
import cn.wavely.http.enums.MethodType;
import cn.wavely.http.http.RequestParams;

import java.lang.annotation.Annotation;

/**
 * @author chenglong
 * @create 2022-07-21 14:54
 * @desc
 **/
public class PathVariableParamsConvert implements ParamsConvert {

    @Override
    public boolean support(Annotation a) {
        if (a != null && a instanceof PathVariable) {
            return true;
        }
        return false;
    }

    @Override
    public void convert(MethodType methodType, String paramName, Object param, RequestParams requestParams) {
        String newUrl = requestParams.getUrl().replaceAll("{" + paramName + "}", param.toString());
        requestParams.setUrl(newUrl);
    }
}
