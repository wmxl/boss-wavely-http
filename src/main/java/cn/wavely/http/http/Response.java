package cn.wavely.http.http;

/**
 * @author chenglong
 * @create 2022-07-22 15:30
 * @desc
 **/
public interface Response {

    /**
     * 是否成功
     * @return
     */
    boolean isSuccess();

    /**
     * 返回的内容
     * @return
     */
    Object body();

    /**
     * 错误信息
     * @return
     */
    String errMsg();
}
