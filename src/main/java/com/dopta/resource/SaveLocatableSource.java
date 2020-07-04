package com.dopta.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class SaveLocatableSource {
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private BigDecimal Latitude;
    @NotNull
    private BigDecimal longitude;
}
