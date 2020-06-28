package com.tutorial.crud.service;


import com.tutorial.crud.model.SubscriptionPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface SubscriptionPlanService {
    SubscriptionPlan getSubscriptionPlanById(Integer subscriptionPlanId);
    Page<SubscriptionPlan> getAllSubscriptionPlans(Pageable pageable);
    SubscriptionPlan createSubscriptionPlan(SubscriptionPlan subscriptionPlan);
    SubscriptionPlan editSubscriptionPlan(SubscriptionPlan subscriptionPlanRequest, Integer subscriptionPlanId);
    ResponseEntity<?> deleteSubscriptionPlanById(Integer subscriptionPlanId);
}
