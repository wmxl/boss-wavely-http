package cn.wavely.http.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author chenglong
 * @create 2022-06-20 21:22
 * @desc
 **/
@WavelyRequest
@Retention(RetentionPolicy.RUNTIME)
public @interface Put {
    String url() default "";

    String[] headers() default "";

    String[] requestParams() default "";

    int retry() default 0;//请求失败重试次数

    Class[] filters() default {};

}
