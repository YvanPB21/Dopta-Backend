package com.tutorial.crud.resource.size;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class SaveSizeResource {
    @NotNull
    private String name;
}
