package com.dopta.repository;

import com.dopta.model.Pet;
import com.dopta.model.Species;
import com.dopta.model.SubscriptionPlan;
import com.dopta.model.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription,Integer> {
    List<UserSubscription> findBySubscriptionPlan(SubscriptionPlan subscriptionPlan);
}
