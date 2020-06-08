package com.dopta.controller;

import com.dopta.model.SubscriptionPlan;
import com.dopta.service.SubscriptionPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/plans")
public class SubscriptionPlanController {
    @Autowired
    private SubscriptionPlanService subscriptionPlanService;
    @GetMapping
    public ResponseEntity<List<SubscriptionPlan>> listSubscriptionPlans()
    {
        List<SubscriptionPlan> subscriptionPlans=subscriptionPlanService.listAllSubscriptionPlans();
        if (subscriptionPlans.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }else
        return ResponseEntity.ok(subscriptionPlans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionPlan>getById(@PathVariable Integer id)
    {
        SubscriptionPlan subscriptionPlan=subscriptionPlanService.getSubscriptionPlan(id);
        if (subscriptionPlan==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(subscriptionPlan));
    }

    @PostMapping
    public ResponseEntity<SubscriptionPlan> newSubscriptionPlan(@RequestBody SubscriptionPlan subscriptionPlan)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionPlanService.save(subscriptionPlan));
    }
    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionPlan> updateSubscriptionPlan(@RequestBody SubscriptionPlan subscriptionPlan, @PathVariable Integer id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionPlanService.edit(subscriptionPlan,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<SubscriptionPlan> deleteSubscriptionPlan(@PathVariable Integer id)
    {
        subscriptionPlanService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
