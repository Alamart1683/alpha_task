package com.application.alpha_task.controller;

import com.application.alpha_task.query.request.GifExchangeRateRequest;
import com.application.alpha_task.service.AlphaTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AlphaTaskController {
    private final AlphaTaskService alphaTaskService;

    @GetMapping("/api/rate/gif")
    void getExchangeRate(
            @Validated @ModelAttribute("gifExchangeRateRequest") GifExchangeRateRequest gifExchangeRateRequest)
    {
        alphaTaskService.getExchangeRateGif(gifExchangeRateRequest.getCurrencyCode());
    }
}
