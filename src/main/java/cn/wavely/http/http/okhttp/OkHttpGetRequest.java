package cn.wavely.http.http.okhttp;

import cn.wavely.http.http.RequestParams;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author chenglong
 * @create 2022-06-29 18:04
 * @desc
 **/
public class OkHttpGetRequest extends AbstractOkHttpRequest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String url;
    private Map<String, String> param;
    private Map<String, String> header;
    private Class returnClass;

    public OkHttpGetRequest(OkHttpClient client, RequestParams params) {
        super(client);
        this.url = params.getUrl();
        this.header = params.getHeaders();
        this.param = params.getRequestParam();
        this.returnClass = params.getReturnClass();
    }

    @Override
    public cn.wavely.http.http.Response execute() {
        Request.Builder builder = new Request.Builder()
                .url(buildUrl(url, param))
                .get();
        buildHeader(builder, header);
        return request(builder.build(), returnClass);
    }
}
