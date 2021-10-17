package com.application.alpha_task.query.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiphyResponseMetaData {
    private String msg;
    private String status;
    private String response_id;
}
