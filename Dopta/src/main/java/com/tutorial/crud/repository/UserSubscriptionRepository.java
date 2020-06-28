package com.tutorial.crud.repository;

import com.tutorial.crud.model.UserSubscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription,Integer> {
    Page<UserSubscription> findAllBySubscriptionPlanId(Integer subscriptionPlanId, Pageable pageable);
    Page<UserSubscription> findAllByUserId(Integer userId, Pageable pageable);
    Page<UserSubscription> findAllBySubscriptionPlanIdAndUserId(Integer subscriptionPlanId, Integer userId, Pageable pageable);
}
