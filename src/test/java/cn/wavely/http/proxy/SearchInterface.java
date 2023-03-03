package cn.wavely.http.proxy;

import cn.wavely.http.annotation.*;

import java.util.List;

/**
 * @author chenglong
 * @create 2022-06-20 21:00
 * @desc
 **/
@WavelyRequest(url = "http://13.52.175.119:9901", headers = {"token:token"}, requestParams = {"accessToken:XFFGw1nk9rOgqj1KfcvVgC2bStG4sySd"})
public interface SearchInterface {

    @Get(url = "/v2/search/searchJobCategoryByLocation", headers = {}, requestParams = {})
    JobCategoryResult searchJobCategoryByLocation(int cityId, List<Integer> tagIdList, int from, int size);

    @Post(url = "/v2/search/searchJobs")
    RecJobResult searchJobs(@RequestBody SearchJobRequest data);
}
