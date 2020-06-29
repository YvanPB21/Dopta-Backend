package com.dopta.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Null;

@Getter
@Setter
public class SaveMessageResource {

    @Null
    private String text;
    @Null
    private String image_url;
    @Null
    private String recording_url;
    @Null
    private String video_url;
}
