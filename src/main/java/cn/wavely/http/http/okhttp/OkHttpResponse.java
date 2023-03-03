package cn.wavely.http.http.okhttp;

import cn.wavely.http.http.Response;
import com.alibaba.fastjson.JSONObject;

/**
 * @author chenglong
 * @create 2022-07-22 15:37
 * @desc
 **/
public class OkHttpResponse implements Response {
    private okhttp3.Response response;
    private boolean isSuccess = true;
    private Object body;

    public OkHttpResponse(okhttp3.Response response, Class returnClass) {
        this.response = response;
        if (returnClass != null && !returnClass.equals(Void.TYPE)) {
            try {
                body = JSONObject.parseObject(response.body().string(), returnClass);
            } catch (Exception e) {
                isSuccess = false;
            }
        }
    }

    @Override
    public boolean isSuccess() {
        if (isSuccess && response.isSuccessful()) {
            return true;
        }
        return false;
    }

    @Override
    public Object body() {
        return body;
    }

    @Override
    public String errMsg() {
        return response.networkResponse().toString();
    }
}
