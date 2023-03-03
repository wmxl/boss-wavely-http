package cn.wavely.http.proxy;

import lombok.Data;

import java.util.List;

public class JobCategoryResult extends BaseRecResult<List<JobCategoryResult.Result>> {
    /*
    "id": 111,
                "parentId": 12,
                "name": "Backend Engineer",
                "canonicalId": -1,
                "level": 2,
                "userDefined": false
     */
    @Data
    public static class Result {
        private Long id;
        private Integer parentId;
        private String name;
        private Integer level;
        private Boolean userDefined;
    }
}
