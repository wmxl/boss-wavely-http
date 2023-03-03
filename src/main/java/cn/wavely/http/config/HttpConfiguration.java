package cn.wavely.http.config;

import lombok.Data;

/**
 * @author chenglong
 * @create 2022-06-20 20:41
 * @desc
 **/
@Data
public class HttpConfiguration {

    private int maxRequest = 200;
    private int maxRequestsPerHost = 20;
    private Long connectionTimeOut= 3000L;
    private Long readTimeOut = 3000L;
}
