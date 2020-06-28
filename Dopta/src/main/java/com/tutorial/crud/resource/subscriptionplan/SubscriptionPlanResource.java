package com.tutorial.crud.resource.subscriptionplan;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SubscriptionPlanResource {
    private Integer id;
    private String name;
    private Integer months;
    private BigDecimal price;
    private BigDecimal discountPercentage;
}
