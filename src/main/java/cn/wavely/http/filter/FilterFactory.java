package cn.wavely.http.filter;

import cn.wavely.http.exception.LoadFilterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenglong
 * @create 2022-07-25 10:18
 * @desc
 **/
public class FilterFactory {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private volatile Map<Class, Filter> filterMap = new HashMap<>();
    private List<Filter> baseFilterList;

    public FilterFactory(List<Filter> baseFilterList) {
        this.baseFilterList = baseFilterList;
    }

    public Filter getFilter(Class filterClass) {
        if (filterMap.containsKey(filterClass)) {
            return filterMap.get(filterClass);
        }
        synchronized (this) {
            if (filterMap.containsKey(filterClass)) {
                return filterMap.get(filterClass);
            }
            try {
                Filter filter = (Filter) filterClass.newInstance();
                filterMap.put(filterClass, filter);
                return filter;
            } catch (Exception e) {
                throw new LoadFilterException("load filter exception class:" + filterClass.getName());
            }
        }
    }

    public List<Filter> getBaseFilterList() {
        return new ArrayList<>(baseFilterList);
    }

}
