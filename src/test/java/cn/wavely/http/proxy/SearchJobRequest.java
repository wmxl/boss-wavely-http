package cn.wavely.http.proxy;

import lombok.Data;

/**
 * @author chenglong
 * @create 2022-07-06 18:21
 * @desc
 **/
@Data
public class SearchJobRequest {
    private Integer talentId;
    private Integer expectGroupId;
    private Integer distance;
    private Integer from;
    private Integer pageSize;
}
