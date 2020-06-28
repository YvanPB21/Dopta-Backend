package com.tutorial.crud.resource.promo;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;

@Data
public class SavePromoResource {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @Column(name="image_url") /*TODO revisar si se quita el column, si no, agregar a otros resources*/
    private String imageUrl;
}
