package com.tutorial.crud.resource.usersubscription;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class SaveUserSubscriptionResource {
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfSubscription;
}
