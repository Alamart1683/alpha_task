package com.application.alpha_task.controller;

import com.application.alpha_task.query.request.GifExchangeRateRequest;
import com.application.alpha_task.query.response.UrlGifResponse;
import com.application.alpha_task.service.AlphaTaskService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.net.URLConnection;

@RestController
@RequiredArgsConstructor
public class AlphaTaskController {
    private final AlphaTaskService alphaTaskService;

    @GetMapping("/exchange_rate/gif/url")
    UrlGifResponse getExchangeRateGifUrl(
            @Validated @ModelAttribute("gifExchangeRateRequest") GifExchangeRateRequest gifExchangeRateRequest
    ) {
        return alphaTaskService.getExchangeRateGif(gifExchangeRateRequest.getCurrencyCode());
    }

    @SneakyThrows
    @GetMapping("/exchange_rate/gif/file")
    void getExchangeRateGifFile(
            @Validated @ModelAttribute("gifExchangeRateRequest") GifExchangeRateRequest gifExchangeRateRequest,
            HttpServletResponse response
    ) {
        UrlGifResponse urlGifResponse = alphaTaskService.getExchangeRateGif(gifExchangeRateRequest.getCurrencyCode());
        String source = urlGifResponse.getUrl();
        URL url = new URL(source);
        URLConnection conn = url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.connect();
        response.setContentType("image/gif");
        response.addHeader("Content-Disposition", "attachment; filename=" + urlGifResponse.getStatus() + ".gif");
        IOUtils.copy(conn.getInputStream(), response.getOutputStream());
    }
}
