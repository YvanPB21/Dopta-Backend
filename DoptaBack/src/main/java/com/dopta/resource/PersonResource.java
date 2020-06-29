package com.dopta.resource;

import com.dopta.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResource extends AuditModel {
    private String names;
    private String last_names;
    private Integer dni;
    private java.util.Date date_of_birth;
}
