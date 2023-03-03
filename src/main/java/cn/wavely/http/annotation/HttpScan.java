package cn.wavely.http.annotation;

import cn.wavely.http.spring.ScannerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author chenglong
 * @create 2022-07-28 14:55
 * @desc
 **/
@Retention(RetentionPolicy.RUNTIME)
@Import({ScannerConfiguration.class})
public @interface HttpScan {

    String[] packages() default {};
}
