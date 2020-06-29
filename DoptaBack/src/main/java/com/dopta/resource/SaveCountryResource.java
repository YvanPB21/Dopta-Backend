package com.dopta.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveCountryResource {
    @NotNull
    private String name;
}
