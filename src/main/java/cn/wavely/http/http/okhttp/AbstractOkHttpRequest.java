package cn.wavely.http.http.okhttp;

import cn.wavely.http.exception.HttpException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author chenglong
 * @create 2022-06-30 15:38
 * @desc
 **/
public abstract class AbstractOkHttpRequest implements cn.wavely.http.http.Request {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private OkHttpClient client;

    public AbstractOkHttpRequest(OkHttpClient client) {
        this.client = client;
    }

    protected String buildUrl(String url, Map<String, String> param) {
        if (!CollectionUtils.isEmpty(param)) {
            StringBuilder sb = new StringBuilder();
            sb.append(url + "?");
            for (Map.Entry<String, String> entry : param.entrySet()) {
                try {
                    sb.append(URLEncoder.encode(entry.getKey(), "utf-8"))
                            .append("=")
                            .append(URLEncoder.encode(String.valueOf(entry.getValue()), "utf-8"))
                            .append("&");
                } catch (UnsupportedEncodingException e) {
                    logger.error("get url:{} param:{}", url, param);
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        } else {
            return url;
        }
    }

    protected void buildHeader(Request.Builder builder, Map<String, String> headers) {
        if (!CollectionUtils.isEmpty(headers)) {
            headers.entrySet().forEach(entry -> builder.addHeader(entry.getKey(), entry.getValue()));
        }
    }

    protected cn.wavely.http.http.Response request(Request request, Class tClass){
        try {
            Response response = client.newCall(request).execute();
            return new OkHttpResponse(response, tClass);
        } catch (Exception e) {
            logger.error("okHttp requestError request:{} e", request, e);
            throw new HttpException(e.toString());
        }
    }
}
