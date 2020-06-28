package com.tutorial.crud.service.impl;


import com.tutorial.crud.entity.SubscriptionPlan;
import com.tutorial.crud.entity.SubscriptionPlan;
import com.tutorial.crud.exception.ResourceNotFoundException;
import com.tutorial.crud.repository.SubscriptionPlanRepository;
import com.tutorial.crud.service.SubscriptionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
/*TODO revisar por fallas en el replace*/
@Service
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    public SubscriptionPlan getSubscriptionPlanById(Integer subscriptionPlanId) {
        return subscriptionPlanRepository.findById(subscriptionPlanId).orElseThrow(()->new ResourceNotFoundException("SubscriptionPlan","Id",subscriptionPlanId));
    }

    @Override
    public Page<SubscriptionPlan> getAllSubscriptionPlans(Pageable pageable) {
        return subscriptionPlanRepository.findAll(pageable);
    }

    @Override
    public SubscriptionPlan createSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        SubscriptionPlan newSubscriptionPlan=new SubscriptionPlan();
        newSubscriptionPlan.setName(subscriptionPlan.getName());
        subscriptionPlan.setMonths(subscriptionPlan.getMonths());
        subscriptionPlan.setDiscountPercentage(subscriptionPlan.getDiscountPercentage());
        subscriptionPlan.setPrice(subscriptionPlan.getPrice());
        return subscriptionPlanRepository.save(newSubscriptionPlan);
    }

    @Override
    public SubscriptionPlan editSubscriptionPlan(SubscriptionPlan subscriptionPlanRequest, Integer subscriptionPlanId) {
        if(!subscriptionPlanRepository.existsById(subscriptionPlanId))
            throw new ResourceNotFoundException("SubscriptionPlan","Id",subscriptionPlanId);
        return subscriptionPlanRepository.findById(subscriptionPlanId).map(subscriptionPlan->
        {
            subscriptionPlan.setName(subscriptionPlanRequest.getName());
            subscriptionPlan.setMonths(subscriptionPlanRequest.getMonths());
            subscriptionPlan.setDiscountPercentage(subscriptionPlanRequest.getDiscountPercentage());
            subscriptionPlan.setPrice(subscriptionPlanRequest.getPrice());
            return subscriptionPlanRepository.save(subscriptionPlan);
        }).orElseThrow(()->new ResourceNotFoundException("SubscriptionPlan","Id",subscriptionPlanId));
    }

    @Override
    public ResponseEntity<?> deleteSubscriptionPlanById(Integer subscriptionPlanId) {
        return subscriptionPlanRepository.findById(subscriptionPlanId).map(subscriptionPlan->{
            subscriptionPlanRepository.delete(subscriptionPlan);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("SubscriptionPlan"));
    }
}
