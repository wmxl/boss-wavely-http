package cn.wavely.http.spring;

import cn.wavely.http.annotation.HttpScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chenglong
 * @create 2022-07-28 16:29
 * @desc
 **/
public class ScannerConfiguration implements ImportBeanDefinitionRegistrar {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static List<String> basePackageList = new ArrayList<>();

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        Map<String, Object> annotationAttributes = importingClassMetadata
                .getAnnotationAttributes(HttpScan.class.getName());
        if (annotationAttributes != null && annotationAttributes.get("packages") != null) {
            for (String str : (String[]) annotationAttributes.get("packages")) {
                basePackageList.add(str);
            }
        }
    }

}
