package com.tutorial.crud.resource;

import com.tutorial.crud.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentResource extends AuditModel {
    private Integer id;
    private String content;
    private java.util.Date date;
}
