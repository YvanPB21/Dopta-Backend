package com.dopta.resource;

import com.dopta.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResource extends AuditModel {
    private Integer id;
    private java.util.Date date;
    private String text;
    private String image_url;
    private String recording_url;
    private String video_url;
}
