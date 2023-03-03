package cn.wavely.http.filter.impl;

import cn.wavely.http.exception.HttpException;
import cn.wavely.http.filter.Filter;
import cn.wavely.http.filter.RequestContext;
import cn.wavely.http.http.Request;
import cn.wavely.http.http.RequestFactory;
import cn.wavely.http.http.Response;

/**
 * @author chenglong
 * @create 2022-07-22 15:26
 * @desc
 **/
public class RequestExecuteFilter implements Filter {

    private RequestFactory requestFactory;

    public RequestExecuteFilter(RequestFactory requestFactory) {
        this.requestFactory =requestFactory;
    }

    @Override
    public int order() {
        return 0;
    }

    @Override
    public boolean filter(RequestContext requestContext) {
        Request request = requestFactory.create(requestContext.getRequestParams());
        int count = requestContext.getRetry() + 1 <= 1 ? 1 : requestContext.getRetry() + 1;
        for (int i = 0; i < count; i++) {
            try {
                Response response = request.execute();
                requestContext.setResponse(response);
                if (response.isSuccess()) {
                    break;
                }
            } catch (HttpException exception) {
            }
        }
        return true;
    }
}
