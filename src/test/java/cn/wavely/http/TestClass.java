package cn.wavely.http;


import cn.wavely.http.annotation.Delete;
import cn.wavely.http.annotation.Put;

import java.lang.annotation.Annotation;

/**
 * @author chenglong
 * @create 2022-06-20 20:59
 * @desc
 **/
@Delete
@Put
public class TestClass {

    public static void main(String[] args) {
        Annotation[] a =new TestClass().getClass().getAnnotations();
        System.out.println(a[0].getClass() + ",  " + a[1].getClass());
    }
}
