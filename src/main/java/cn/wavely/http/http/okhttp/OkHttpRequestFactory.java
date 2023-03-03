package cn.wavely.http.http.okhttp;

import cn.wavely.http.config.HttpConfiguration;
import cn.wavely.http.http.Request;
import cn.wavely.http.http.RequestFactory;
import cn.wavely.http.http.RequestParams;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @author chenglong
 * @create 2022-06-29 18:07
 * @desc
 **/
public class OkHttpRequestFactory implements RequestFactory {

    private OkHttpClient client;

    public OkHttpRequestFactory(HttpConfiguration config) {
        client = new OkHttpClient.Builder().connectTimeout(config.getConnectionTimeOut(), TimeUnit.MILLISECONDS)
                .readTimeout(config.getReadTimeOut(), TimeUnit.MILLISECONDS)
                .build();
        client.dispatcher().setMaxRequests(config.getMaxRequest());
        client.dispatcher().setMaxRequestsPerHost(config.getMaxRequestsPerHost());

    }

    @Override
    public Request create(RequestParams params) {
        switch (params.getMethodType()) {
            case GET:
                return new OkHttpGetRequest(client, params);
            case POST:
                return new OkHttpPostRequest(client, params);
            case PUT:
                return new OkHttpPutRequest(client, params);
            case DELETE:
                return new OkHttpDeleteRequest(client, params);
        }
        return null;
    }
}
