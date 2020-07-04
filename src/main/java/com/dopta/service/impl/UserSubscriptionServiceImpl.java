package com.dopta.service.impl;


import com.dopta.exception.ResourceNotFoundException;
import com.dopta.model.UserSubscription;
import com.dopta.repository.SubscriptionPlanRepository;
import com.dopta.repository.UserRepository;
import com.dopta.repository.UserSubscriptionRepository;
import com.dopta.service.UserSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserSubscriptionServiceImpl implements UserSubscriptionService {
    @Autowired
    private UserSubscriptionRepository userSubscriptionRepository;
    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserSubscription getUserSubscriptionById(Integer userSubscriptionId) {
        return userSubscriptionRepository.findById(userSubscriptionId).orElseThrow(() -> new ResourceNotFoundException("User Subscription", "Id", userSubscriptionId));
    }

    @Override
    public Page<UserSubscription> getUserSubscriptionByUserId(Integer userId, Pageable pageable) {
        return userSubscriptionRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public Page<UserSubscription> getUserSubscriptionBySubscriptionPlanId(Integer subscriptionPlanId, Pageable pageable) {
        return userSubscriptionRepository.findAllBySubscriptionPlanId(subscriptionPlanId, pageable);
    }

    @Override
    public Page<UserSubscription> getUserSubscriptionByUserIdAndSubscriptionPlanId(Integer userId, Integer subscriptionPlanId, Pageable pageable) {
        return userSubscriptionRepository.findAllBySubscriptionPlanIdAndUserId(subscriptionPlanId, userId, pageable);
    }

    @Override
    public Page<UserSubscription> getAllSubscriptions(Pageable pageable) {
        return userSubscriptionRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public UserSubscription createUserSubscription(UserSubscription userSubscription, Integer userId, Integer subscriptionPlanId) {
        UserSubscription newUserSubscription = new UserSubscription();
        newUserSubscription.setDateOfSubscription(userSubscription.getDateOfSubscription());
        newUserSubscription.setSubscriptionPlan(subscriptionPlanRepository.findById(subscriptionPlanId).orElseThrow(() -> new ResourceNotFoundException("Subscription Plan", "Id", subscriptionPlanId)));
        newUserSubscription.setUser(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId)));
        return userSubscriptionRepository.save(newUserSubscription);
    }

    @Override
    @Transactional
    public UserSubscription editUserSubscription(UserSubscription userSubscription, Integer userSubscriptionId, Integer userId, Integer subscriptionPlanId) {
        if (!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        if (!subscriptionPlanRepository.existsById(subscriptionPlanId))
            throw new ResourceNotFoundException("Subscription Plan", "Id", subscriptionPlanId);
        return userSubscriptionRepository.findById(userSubscriptionId).map(us -> {
            us.setDateOfSubscription(userSubscription.getDateOfSubscription());
            us.setSubscriptionPlan(subscriptionPlanRepository.findById(subscriptionPlanId).orElseThrow(() -> new ResourceNotFoundException("Subscription Plan", "Id", subscriptionPlanId)));
            us.setUser(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId)));
            return userSubscriptionRepository.save(us);
        }).orElseThrow(() -> new ResourceNotFoundException("User Subscription", "Id", userSubscriptionId));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteUserSubscriptionById(Integer userSubscriptionId) {
        return userSubscriptionRepository.findById(userSubscriptionId).map(us -> {
            userSubscriptionRepository.delete(us);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(("User Subscription")));
    }
}
