package com.application.alpha_task.query.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiphyResponse {
    private GiphyResponseData data;
    private GiphyResponseMetaData meta;
}
