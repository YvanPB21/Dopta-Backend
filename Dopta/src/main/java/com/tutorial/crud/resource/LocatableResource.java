package com.tutorial.crud.resource;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class LocatableResource {
    private String name;
    private String address;
    private BigDecimal Latitude;
    private BigDecimal longitude;
}
