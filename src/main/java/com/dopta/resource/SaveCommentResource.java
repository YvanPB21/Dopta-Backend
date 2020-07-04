package com.dopta.resource;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveCommentResource {
    @NotNull
    private String content;
    @NotNull
    private java.util.Date date;
}
