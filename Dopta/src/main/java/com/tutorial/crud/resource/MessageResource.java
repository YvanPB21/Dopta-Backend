package com.tutorial.crud.resource;

import com.sun.istack.NotNull;
import com.tutorial.crud.model.AuditModel;
import com.tutorial.crud.model.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Null;

@Getter @Setter
public class MessageResource extends AuditModel {
    private Integer id;
    private java.util.Date date;
    private String text;
    private String image_url;
    private String recording_url;
    private String video_url;
}
