package com.tutorial.crud.service.impl;


import com.tutorial.crud.entity.SubscriptionPlan;
import com.tutorial.crud.entity.UserSubscription;
import com.tutorial.crud.exception.ResourceNotFoundException;
import com.tutorial.crud.repository.SubscriptionPlanRepository;
import com.tutorial.crud.repository.UserRepository;
import com.tutorial.crud.repository.UserSubscriptionRepository;
import com.tutorial.crud.service.UserSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        return userSubscriptionRepository.findById(userSubscriptionId).orElseThrow(()->new ResourceNotFoundException("User Subscription","Id",userSubscriptionId));
    }

    @Override
    public Page<UserSubscription> getUserSubscriptionByUserId(Integer userId, Pageable pageable) {
        return userSubscriptionRepository.findAllByUserId(userId,pageable);
    }

    @Override
    public Page<UserSubscription> getUserSubscriptionBySubscriptionPlanId(Integer subscriptionPlanId, Pageable pageable) {
        return userSubscriptionRepository.findAllBySubscriptionPlanId(subscriptionPlanId,pageable);
    }

    @Override
    public Page<UserSubscription> getUserSubscriptionByUserIdAndSubscriptionPlanId(Integer userId, Integer subscriptionPlanId, Pageable pageable) {
        return userSubscriptionRepository.findAllBySubscriptionPlanIdAndUserId(subscriptionPlanId,userId,pageable);
    }

    @Override
    public Page<UserSubscription> getAllSubscriptions(Pageable pageable) {
        return userSubscriptionRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public UserSubscription createUserSubscription(UserSubscription userSubscription, Integer userId, Integer subscriptionPlanId) {
        UserSubscription newUserSubscription=new UserSubscription();
        newUserSubscription.setDateOfSubscription(userSubscription.getDateOfSubscription());
        newUserSubscription.setSubscriptionPlan(subscriptionPlanRepository.findById(subscriptionPlanId).orElseThrow(()->new ResourceNotFoundException("Subscription Plan","Id",subscriptionPlanId)));
        newUserSubscription.setUser(userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId)));
        return userSubscriptionRepository.save(newUserSubscription);
    }

    @Override
    @Transactional
    public UserSubscription editUserSubscription(UserSubscription userSubscription, Integer userSubscriptionId, Integer userId, Integer subscriptionPlanId) {
        if(!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User","Id",userId);
        if(!subscriptionPlanRepository.existsById(subscriptionPlanId))
            throw new ResourceNotFoundException("Subscription Plan","Id",subscriptionPlanId);
        return userSubscriptionRepository.findById(userSubscriptionId).map(us->{
            us.setDateOfSubscription(userSubscription.getDateOfSubscription());
            us.setSubscriptionPlan(subscriptionPlanRepository.findById(subscriptionPlanId).orElseThrow(()->new ResourceNotFoundException("Subscription Plan","Id",subscriptionPlanId)));
            us.setUser(userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId)));
            return userSubscriptionRepository.save(us);
        }).orElseThrow(()->new ResourceNotFoundException("User Subscription","Id",userSubscriptionId));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteUserSubscriptionById(Integer userSubscriptionId) {
        return userSubscriptionRepository.findById(userSubscriptionId).map(us->{
            userSubscriptionRepository.delete(us);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(("User Subscription")));
    }
}
