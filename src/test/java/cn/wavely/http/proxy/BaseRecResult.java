package cn.wavely.http.proxy;

import lombok.Data;

/**
 * @author chenglong
 * @create 2021-02-08 16:17
 * @desc
 **/
@Data
public class BaseRecResult<T> {
    private Integer status;
    private String error;
    private T data;

    private static final int SUCCESS_STATUS = 0;

    public boolean isSuccess() {
        return status != null && status == SUCCESS_STATUS;
    }
}
