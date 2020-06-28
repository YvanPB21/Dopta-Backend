package com.tutorial.crud.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class SaveDistrictResource {
@NotNull
    private String name;
}
