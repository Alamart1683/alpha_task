package com.application.alpha_task.client;

import com.application.alpha_task.query.response.GiphyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gifClient", url = "${gif.url}")
public interface GifClient {

    @GetMapping("/random")
    GiphyResponse getRandomGif(
            @RequestParam String api_key,
            @RequestParam String tag,
            @RequestParam String rating
    );
}
