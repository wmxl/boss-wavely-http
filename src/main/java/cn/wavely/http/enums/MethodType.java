package cn.wavely.http.enums;

import cn.wavely.http.annotation.Delete;
import cn.wavely.http.annotation.Get;
import cn.wavely.http.annotation.Post;
import cn.wavely.http.annotation.Put;

import java.lang.annotation.Annotation;

/**
 * @author chenglong
 * @create 2022-06-21 21:17
 * @desc
 **/
public enum MethodType {
    GET("Get"),
    POST("Post"),
    PUT("Put"),
    DELETE("Delete");

    private String name;

    MethodType(String name) {
        this.name = name;
    }

    public static MethodType getByHttpAnnotation(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof Get) {
                return GET;
            } else if (annotation instanceof Post) {
                return POST;
            } else if (annotation instanceof Put) {
                return PUT;
            } else if (annotation instanceof Delete) {
                return DELETE;
            }
        }
        return GET;
    }
}
