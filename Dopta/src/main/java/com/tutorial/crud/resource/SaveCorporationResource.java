package com.tutorial.crud.resource;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SaveCorporationResource {
    @NotNull
    private String corporationName;
    @NotNull
    private Integer ruc;
}
