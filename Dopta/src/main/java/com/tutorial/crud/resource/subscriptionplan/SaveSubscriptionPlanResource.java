package com.tutorial.crud.resource.subscriptionplan;

import com.sun.istack.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaveSubscriptionPlanResource {

    @NotNull
    private String name;

    @NotNull
    private Integer months;

    @NotNull
    private BigDecimal price;

    private BigDecimal discountPercentage;
}
