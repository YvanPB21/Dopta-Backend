package com.tutorial.crud.resource.species;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class SaveSpeciesResource {
    @NotNull
    private String name;
}
