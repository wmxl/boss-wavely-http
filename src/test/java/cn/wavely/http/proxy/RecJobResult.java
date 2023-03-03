package cn.wavely.http.proxy;

import lombok.Data;

import java.util.List;

/**
 * @author chenglong
 * @create 2021-02-08 16:31
 * @desc
 **/
public class RecJobResult extends BaseRecResult<RecJobResult.Result>{


    @Data
    public static class Result {
        private Integer from;
        private Integer pageSize;
        private Integer total;
        private List<Job> jobs;
    }

    @Data
    public static class Job {
        private Long id;
        private boolean featured;
        private Long relevantExpectId;
    }
}
