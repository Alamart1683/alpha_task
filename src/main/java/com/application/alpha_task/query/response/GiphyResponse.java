package com.application.alpha_task.query.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GiphyResponse {
    private GiphyResponseData data;
    private GiphyResponseMetaData meta;
}
