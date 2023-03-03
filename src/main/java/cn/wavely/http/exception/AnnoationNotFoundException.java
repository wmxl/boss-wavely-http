package cn.wavely.http.exception;

/**
 * @author chenglong
 * @create 2022-06-21 21:07
 * @desc
 **/
public class AnnoationNotFoundException extends RuntimeException {

    public AnnoationNotFoundException (String className, String annotation) {
        super(String.format("{} not found {} annotation", className, annotation));
    }
}
