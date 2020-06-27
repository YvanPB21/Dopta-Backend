package com.tutorial.crud.resource;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentResource {
    private Integer id;
    private String content;
    private java.util.Date date;
}
