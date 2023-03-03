package cn.wavely.http.http;

import cn.wavely.http.enums.MethodType;
import lombok.Data;

import java.util.Map;

/**
 * @author chenglong
 * @create 2022-06-30 21:00
 * @desc
 **/
@Data
public class RequestParams {
    private String url;
    private Map<String, String> headers;
    private Map<String, String> requestParam;
    private Object requestBody;
    private MethodType methodType;
    private Class returnClass;
}
