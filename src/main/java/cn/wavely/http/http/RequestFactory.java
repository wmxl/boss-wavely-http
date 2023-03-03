package cn.wavely.http.http;

import cn.wavely.http.enums.MethodType;

import java.util.Map;

/**
 * @author chenglong
 * @create 2022-06-29 18:03
 * @desc
 **/
public interface RequestFactory {

    Request create(RequestParams params);
}
