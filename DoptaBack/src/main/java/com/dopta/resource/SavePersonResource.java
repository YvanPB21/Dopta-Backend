package com.dopta.resource;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavePersonResource {
    @NotNull
    private String names;
    @NotNull
    private String last_names;
    @NotNull
    private Integer dni;
    @NotNull
    private java.util.Date date_of_birth;
}