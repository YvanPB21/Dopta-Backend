package com.tutorial.crud.resource;

import com.tutorial.crud.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GenderResource extends AuditModel {
    private Integer id;
    private String name;
}
