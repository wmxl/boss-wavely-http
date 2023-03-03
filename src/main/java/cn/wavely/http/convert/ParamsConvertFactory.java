package cn.wavely.http.convert;

import cn.wavely.http.convert.annotation.HeaderParamsConvert;
import cn.wavely.http.convert.annotation.PathVariableParamsConvert;
import cn.wavely.http.convert.annotation.RequestBodyParamsConvert;
import cn.wavely.http.convert.annotation.RequestParamParamsConvert;
import cn.wavely.http.enums.MethodType;
import cn.wavely.http.http.RequestParams;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenglong
 * @create 2022-07-20 15:51
 * @desc
 **/

public class ParamsConvertFactory {
    private List<ParamsConvert> convertList;

    private final static class Holder {
        public static final ParamsConvertFactory INSTANCE = new ParamsConvertFactory();
    }

    public static ParamsConvertFactory getInstance() {
        return Holder.INSTANCE;
    }

    private ParamsConvertFactory() {
        convertList = new ArrayList<>();
        convertList.add(new RequestParamParamsConvert());
        convertList.add(new RequestBodyParamsConvert());
        convertList.add(new PathVariableParamsConvert());
        convertList.add(new DefaultParamsConvert());
        convertList.add(new HeaderParamsConvert());
    }

    public void convert(Annotation annotation, MethodType methodType, String paramName, Object param, RequestParams requestParams) {
        for (int i = 0; i < convertList.size(); i ++) {
            ParamsConvert paramsConvert = convertList.get(i);
            if (paramsConvert.support(annotation)) {
                paramsConvert.convert(methodType, paramName, param, requestParams);
                break;
            }
        }
    }
}
