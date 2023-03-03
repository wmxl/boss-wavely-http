package cn.wavely.http.exception;

/**
 * @author chenglong
 * @create 2022-07-21 15:54
 * @desc
 **/
public class HttpException extends RuntimeException {

    public HttpException(String msg) {
        super(msg);
    }
}
