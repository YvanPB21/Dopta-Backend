package com.dopta.resource.size;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class SaveSizeResource {
    @NotNull
    private String name;
}
