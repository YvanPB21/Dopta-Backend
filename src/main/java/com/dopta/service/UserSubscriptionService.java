package com.dopta.service;

import com.dopta.model.UserSubscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserSubscriptionService {
    UserSubscription getUserSubscriptionById(Integer userSubscriptionId);

    Page<UserSubscription> getUserSubscriptionByUserId(Integer userId, Pageable pageable);

    Page<UserSubscription> getUserSubscriptionBySubscriptionPlanId(Integer subscriptionPlanId, Pageable pageable);

    Page<UserSubscription> getUserSubscriptionByUserIdAndSubscriptionPlanId(Integer userId, Integer subscriptionPlanId, Pageable pageable);

    Page<UserSubscription> getAllSubscriptions(Pageable pageable);

    UserSubscription createUserSubscription(UserSubscription userSubscription, Integer userId, Integer subscriptionPlanId);

    UserSubscription editUserSubscription(UserSubscription userSubscription, Integer userSubscriptionId, Integer userId, Integer subscriptionPlanId);

    ResponseEntity<?> deleteUserSubscriptionById(Integer userSubscriptionId);
}
