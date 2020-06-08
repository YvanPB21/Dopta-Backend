package com.dopta.service;

import com.dopta.model.Pet;
import com.dopta.model.Species;
import com.dopta.model.SubscriptionPlan;
import com.dopta.model.UserSubscription;

import java.util.List;
import java.util.Optional;

public interface UserSubscriptionService {
    UserSubscription getUserSubscription(Integer id);
    UserSubscription save(UserSubscription userSubscription);
    Optional<UserSubscription> findById(Integer id);
    List<UserSubscription> listAllUserSubscriptions();
    UserSubscription edit(UserSubscription userSubscription, Integer id);
    List<UserSubscription>findBySubscriptionPlan(SubscriptionPlan subscriptionPlan);
    void deleteById(Integer id);
}
