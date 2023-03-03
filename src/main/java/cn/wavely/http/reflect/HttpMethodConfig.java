package cn.wavely.http.reflect;

import cn.wavely.http.annotation.WavelyRequest;
import lombok.Data;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenglong
 * @create 2022-06-20 21:18
 * @desc
 **/
@Data
public class HttpMethodConfig {
    private String url;
    private Map<String, String> headers;
    private Map<String, String> requestParams;
    private int retry;

    public HttpMethodConfig(WavelyRequest classConfig, WavelyRequest methodConfig, DefaultListableBeanFactory beanFactory) {
        String url = classConfig.url();
        String[] headers = classConfig.headers();
        String[] params = classConfig.requestParams();

        this.url = replaceEmbeddedValue(url + methodConfig.url(), beanFactory);

        Map<String, String> headerMap = new HashMap<>();
        fullMapByStringArray(headerMap, headers, beanFactory);
        fullMapByStringArray(headerMap, methodConfig.headers(), beanFactory);
        this.headers = headerMap;

        Map<String, String> paramMap = new HashMap<>();
        fullMapByStringArray(paramMap, params, beanFactory);
        fullMapByStringArray(paramMap, methodConfig.requestParams(), beanFactory);
        this.requestParams = paramMap;

        if (classConfig.retry() > methodConfig.retry()) {
            retry = classConfig.retry();
        } else {
            retry = methodConfig.retry();
        }
    }

    private void fullMapByStringArray(Map<String, String> map, String[] array, DefaultListableBeanFactory beanFactory) {
        if (array != null && array.length > 0) {
            for (String str : array) {
                if (!StringUtils.hasLength(StringUtils.trimAllWhitespace(str))) {
                    continue;
                }
                String[] split = str.split(":");
                map.put(replaceEmbeddedValue(split[0], beanFactory), replaceEmbeddedValue(split[1], beanFactory));
            }
        }
    }

    private String replaceEmbeddedValue(String content, DefaultListableBeanFactory beanFactory) {
        String pattern = "\\$\\{(.*?)}";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(content);
        StringBuffer sr = new StringBuffer();
        while (m.find()) {
            String group = m.group();
            String val = beanFactory.resolveEmbeddedValue(group);
            if (StringUtils.hasLength(val)) {
                m.appendReplacement(sr, val);
            }
        }
        m.appendTail(sr);
        return sr.toString();
    }
}
