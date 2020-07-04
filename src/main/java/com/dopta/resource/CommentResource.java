package com.dopta.resource;

import com.dopta.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResource extends AuditModel {
    private Integer id;
    private String content;
    private java.util.Date date;
    private UserResource commenter;
}
