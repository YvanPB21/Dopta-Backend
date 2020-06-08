package com.dopta.service;

import com.dopta.model.SubscriptionPlan;
import java.util.List;
import java.util.Optional;

public interface SubscriptionPlanService {
    SubscriptionPlan getSubscriptionPlan(Integer id);
    SubscriptionPlan save(SubscriptionPlan subscriptionPlan);
    Optional<SubscriptionPlan> findById(Integer id);
    List<SubscriptionPlan> listAllSubscriptionPlans();
    SubscriptionPlan edit(SubscriptionPlan subscriptionPlan, Integer id);
    void deleteById(Integer id);
}
