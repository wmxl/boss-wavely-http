package cn.wavely.http.filter;

import cn.wavely.http.http.RequestParams;
import cn.wavely.http.http.Response;
import lombok.Data;

/**
 * @author chenglong
 * @create 2022-07-22 14:05
 * @desc
 **/
@Data
public class RequestContext {
    private RequestParams requestParams;
    private int retry;
    private Response response;
}
