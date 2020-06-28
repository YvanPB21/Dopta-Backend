package com.tutorial.crud.resource.promocode;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;

@Data
public class SavePromoCodeResource {

    @Column(name="is_used")
    @NotNull
    private Byte isUsed;
}
