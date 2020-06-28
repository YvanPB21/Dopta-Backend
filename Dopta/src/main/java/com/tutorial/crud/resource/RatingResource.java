package com.tutorial.crud.resource;

import com.tutorial.crud.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class RatingResource extends AuditModel {
    private BigDecimal rating;
}
