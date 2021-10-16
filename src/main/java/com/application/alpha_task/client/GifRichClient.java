package com.application.alpha_task.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "gifRichClient")
public interface GifRichClient {

}
