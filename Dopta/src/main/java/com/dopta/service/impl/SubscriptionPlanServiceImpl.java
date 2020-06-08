package com.dopta.service.impl;

import com.dopta.model.SubscriptionPlan;
import com.dopta.repository.SubscriptionPlanRepository;
import com.dopta.service.SubscriptionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    public SubscriptionPlan getSubscriptionPlan(Integer id) {
        return subscriptionPlanRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public SubscriptionPlan save(SubscriptionPlan subscriptionPlan) {
        return subscriptionPlanRepository.save(subscriptionPlan);
    }

    @Override
    public Optional<SubscriptionPlan> findById(Integer id) {
        return subscriptionPlanRepository.findById(id);
    }

    @Override
    public List<SubscriptionPlan> listAllSubscriptionPlans() {
        return subscriptionPlanRepository.findAll();
    }

    @Override
    @Transactional
    public SubscriptionPlan edit(SubscriptionPlan subscriptionPlan, Integer id) {
        return subscriptionPlanRepository.findById(id).map(sp->{
            sp.setMonths(subscriptionPlan.getMonths());
            sp.setName(subscriptionPlan.getName());
            sp.setPrice(subscriptionPlan.getPrice());
            sp.setDiscountPercentage(subscriptionPlan.getDiscountPercentage());
            return subscriptionPlanRepository.save(sp);
        }).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        subscriptionPlanRepository.deleteById(id);
    }
}
