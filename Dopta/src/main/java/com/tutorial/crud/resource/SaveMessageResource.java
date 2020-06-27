package com.tutorial.crud.resource;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Null;

@Getter @Setter
public class SaveMessageResource {
    @NotNull
    private java.util.Date date;
    @Null
    private String text;
}
