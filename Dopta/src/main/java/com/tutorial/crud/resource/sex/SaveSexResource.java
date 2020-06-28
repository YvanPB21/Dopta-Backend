package com.tutorial.crud.resource.sex;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class SaveSexResource {
    @NotNull
    private String name;
}
