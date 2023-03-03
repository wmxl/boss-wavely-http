package cn.wavely.http.http.okhttp;

import cn.wavely.http.http.RequestParams;
import cn.wavely.http.http.Response;
import com.alibaba.fastjson.JSONObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.Map;

/**
 * @author chenglong
 * @create 2022-06-30 21:25
 * @desc
 **/
public class OkHttpDeleteRequest extends AbstractOkHttpRequest {

    private String url;
    private Map<String, String> requestParam;
    private Object requestBody;
    private Map<String, String> header;
    private Class returnClass;

    public OkHttpDeleteRequest(OkHttpClient client, RequestParams params) {
        super(client);
        this.url = params.getUrl();
        this.header = params.getHeaders();
        this.requestParam = params.getRequestParam();
        this.requestBody = params.getRequestBody();
        this.returnClass = params.getReturnClass();
    }

    @Override
    public Response execute() {
        Request.Builder delete = new Request.Builder()
                .url(buildUrl(url, requestParam));
        if (requestBody != null) {
            delete.delete(RequestBody.create(JSONObject.toJSONString(requestBody), MediaType.get("application/json; charset=utf-8")));
        }
        buildHeader(delete, header);
        return request(delete.build(), returnClass);
    }
}
