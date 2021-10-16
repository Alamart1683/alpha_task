package com.application.alpha_task.query.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
@AllArgsConstructor
public class GiphyResponseData {
    private String type;
    private String id;
    private String url;
    private String slug;
    private String bitly_gif_url;
    private String bitly_url;
    private String embed_url;
    private String username;
    private String source;
    private String title;
    private String rating;
    private String content_url;
    private String source_tld;
    private String source_post_url;
    private String is_sticker;
    private String import_datetime;
    private String trending_datetime;
    private HashMap<String, HashMap<String, String>> images;
    private HashMap<String, String> user;
    private String image_original_url;
    private String image_url;
    private String image_mp4_url;
    private String image_frames;
    private String image_width;
    private String image_height;
    private String fixed_height_downsampled_url;
    private String fixed_height_downsampled_width;
    private String fixed_height_downsampled_height;
    private String fixed_width_downsampled_url;
    private String fixed_width_downsampled_width;
    private String fixed_width_downsampled_height;
    private String fixed_height_small_url;
    private String fixed_height_small_still_url;
    private String fixed_height_small_width;
    private String fixed_height_small_height;
    private String fixed_width_small_url;
    private String fixed_width_small_still_url;
    private String fixed_width_small_width;
    private String fixed_width_small_height;
    private String caption;
}
