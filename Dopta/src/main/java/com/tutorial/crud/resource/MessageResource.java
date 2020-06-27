package com.tutorial.crud.resource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MessageResource {
    private java.util.Date date;
    private String text;
    private String image_url;
    private String recording_url;
    private String video_url;
}
