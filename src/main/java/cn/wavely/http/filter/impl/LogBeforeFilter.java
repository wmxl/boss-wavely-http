package cn.wavely.http.filter.impl;

import cn.wavely.http.filter.Filter;
import cn.wavely.http.filter.RequestContext;
import cn.wavely.http.http.RequestParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenglong
 * @create 2022-07-27 15:18
 * @desc
 **/
public class LogBeforeFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public int order() {
        return 10;
    }

    @Override
    public boolean filter(RequestContext requestContext) {
        RequestParams params = requestContext.getRequestParams();
        logger.info("url:{} retry:{} method:{} requestParam:{} requestBody:{}", params.getUrl(), requestContext.getRetry(), params.getMethodType(), params.getRequestParam(), params.getRequestBody());
        return true;
    }
}
