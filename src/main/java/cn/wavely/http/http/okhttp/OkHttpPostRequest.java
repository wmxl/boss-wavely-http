package cn.wavely.http.http.okhttp;

import cn.wavely.http.http.RequestParams;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.util.Map;

/**
 * @author chenglong
 * @create 2022-06-30 21:25
 * @desc
 **/
public class OkHttpPostRequest extends AbstractOkHttpRequest {

    private String url;
    private Map<String, String> requestParam;
    private Object requestBody;
    private Map<String, String> header;
    private Class returnClass;

    public OkHttpPostRequest(OkHttpClient client, RequestParams params) {
        super(client);
        this.url = params.getUrl();
        this.header = params.getHeaders();
        this.requestParam = params.getRequestParam();
        this.requestBody = params.getRequestBody();
        this.returnClass = params.getReturnClass();
    }

    @Override
    public cn.wavely.http.http.Response execute() {
        Request.Builder post = new Request.Builder()
                .url(buildUrl(url, requestParam));
        if (requestBody != null) {
            post.post(RequestBody.create(JSONObject.toJSONString(requestBody), MediaType.get("application/json; charset=utf-8")));
        }
        buildHeader(post, header);
        return request(post.build(), returnClass);
    }
}
