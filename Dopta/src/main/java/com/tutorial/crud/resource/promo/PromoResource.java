package com.tutorial.crud.resource.promo;

import com.sun.istack.NotNull;
import com.tutorial.crud.entity.Corporation;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class PromoResource {
    private Integer id;
    private String name;
    private String description;
    private String imageUrl;
}
