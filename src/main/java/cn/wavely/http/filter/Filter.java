package cn.wavely.http.filter;

/**
 * @author chenglong
 * @create 2022-07-22 14:03
 * @desc
 **/
public interface Filter {

    /**
     * 按数字倒序 order为0:是真正执行request
     * @return
     */
    int order();

    /**
     * 执行过滤
     * @param requestContext
     */
    boolean filter(RequestContext requestContext);
}
