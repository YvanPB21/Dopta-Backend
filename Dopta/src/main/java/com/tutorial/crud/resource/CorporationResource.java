package com.tutorial.crud.resource;

import com.tutorial.crud.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CorporationResource extends AuditModel {
    private String corporationName;
    private Integer ruc;
}
