package cn.wavely.http.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.Collection;
import java.util.Map;

/**
 * @author chenglong
 * @create 2022-07-20 18:01
 * @desc
 **/
public class ParamsConvertUtil {

    public static void fillMap(Object param, String paramName, Map data) {
        if (param == null) {
            return;
        }
        if (param instanceof Map) {
            data.putAll((Map)param);
        } else if (ClassUtil.isBaseClass(param)) {
            data.put(paramName, param);
        } else if (param instanceof Collection) {
            Object obj = ((Collection)param).stream().map(String::valueOf).reduce((a, b) -> a + "," + b).orElse("");
            data.put(paramName, obj);
        } else {
            data.putAll((JSONObject)JSONObject.toJSON(param));
        }
    }

}
