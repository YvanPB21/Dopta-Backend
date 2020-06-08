package com.dopta.service.impl;

import com.dopta.model.SubscriptionPlan;
import com.dopta.model.UserSubscription;
import com.dopta.repository.UserSubscriptionRepository;
import com.dopta.service.UserSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserSubscriptionServiceImpl implements UserSubscriptionService {
    @Autowired
    private UserSubscriptionRepository userSubscriptionRepository;

    @Override
    public UserSubscription getUserSubscription(Integer id) {
        return userSubscriptionRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public UserSubscription save(UserSubscription userSubscription) {
        return userSubscriptionRepository.save(userSubscription);
    }

    @Override
    public Optional<UserSubscription> findById(Integer id) {
        return userSubscriptionRepository.findById(id);
    }

    @Override
    public List<UserSubscription> listAllUserSubscriptions() {
        return userSubscriptionRepository.findAll();
    }

    @Override
    @Transactional
    public UserSubscription edit(UserSubscription userSubscription, Integer id) {
        return userSubscriptionRepository.findById(id).map(us->{
            us.setDateOfSubscription(userSubscription.getDateOfSubscription());
            us.setSubscriptionPlan(userSubscription.getSubscriptionPlan());
            us.setUser(userSubscription.getUser());
            return userSubscriptionRepository.save(us);
        }).orElse(null);
    }

    @Override
    public List<UserSubscription> findBySubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        return userSubscriptionRepository.findBySubscriptionPlan(subscriptionPlan);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
    userSubscriptionRepository.deleteById(id);
    }
}
