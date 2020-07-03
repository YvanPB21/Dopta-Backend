package com.dopta.resource.promo;

import com.dopta.model.Corporation;
import lombok.Data;

@Data
public class PromoResource {
    private Integer id;
    private String name;
    private String description;
    private String imageUrl;
    private Corporation corporation;
}
