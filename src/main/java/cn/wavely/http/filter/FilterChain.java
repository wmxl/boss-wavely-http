package cn.wavely.http.filter;

import java.util.List;

/**
 * @author chenglong
 * @create 2022-07-22 15:17
 * @desc
 **/
public class FilterChain {

    private List<Filter> filterList;

    public FilterChain(List<Filter> filterList) {
        this.filterList = filterList;
    }

    public void filter(RequestContext requestContext) {
        for (int i = 0; i < filterList.size(); i ++) {
            if (!filterList.get(i).filter(requestContext)) {
                break;
            }
        }
    }
}
