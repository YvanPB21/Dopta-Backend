package com.tutorial.crud.service;

import com.tutorial.crud.entity.UserSubscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserSubscriptionService {
    UserSubscription getUserSubscriptionById(Integer userSubscriptionId);
    Page<UserSubscription> getUserSubscriptionByUserId(Integer userId, Pageable pageable);
    Page<UserSubscription> getUserSubscriptionBySubscriptionPlanId(Integer subscriptionPlanId, Pageable pageable);
    Page<UserSubscription> getUserSubscriptionByUserIdAndSubscriptionPlanId(Integer userId, Integer subscriptionPlanId, Pageable pageable);
    UserSubscription createUserSubscription(UserSubscription userSubscription, Integer userId, Integer subscriptionPlanId);
    UserSubscription editUserSubscription(UserSubscription userSubscription, Integer userSubscriptionId, Integer userId, Integer subscriptionPlanId);
    ResponseEntity<?> deleteUserSubscriptionById(Integer userSubscriptionId);
}
