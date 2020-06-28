package com.tutorial.crud.resource.promo;

import com.sun.istack.NotNull;
import com.tutorial.crud.entity.Corporation;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class SavePromoResource {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @Column(name="image_url") /*TODO revisar si se quita el column, si no, agregar a otros resources*/
    private String imageUrl;
}
