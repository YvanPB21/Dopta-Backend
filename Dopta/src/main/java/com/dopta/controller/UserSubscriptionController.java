package com.dopta.controller;

import com.dopta.model.SubscriptionPlan;
import com.dopta.model.User;
import com.dopta.model.UserSubscription;
import com.dopta.service.UserSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usersubscriptions")
public class UserSubscriptionController {

    @Autowired
    private UserSubscriptionService userSubscriptionService;

    @GetMapping
    public ResponseEntity<List<UserSubscription>> listSubscriptions(@RequestParam(name="SubscriptionId", required=false)Integer subscriptionId)
    {
        List<UserSubscription>userSubscriptions=new ArrayList<>();
        if (null==subscriptionId){
            userSubscriptions=userSubscriptionService.listAllUserSubscriptions();
            if (userSubscriptions.isEmpty())
            {
                return ResponseEntity.noContent().build();
            }
        }else
        {
            userSubscriptions=userSubscriptionService.findBySubscriptionPlan(SubscriptionPlan.builder().id(subscriptionId).build());
        }
        return ResponseEntity.ok(userSubscriptions);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserSubscription>getById(@PathVariable Integer id)
    {
        UserSubscription userSubscription=userSubscriptionService.getUserSubscription(id);
        if(userSubscription==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(userSubscription));
    }
    @PostMapping
    public ResponseEntity<UserSubscription> newUserSubscription(@RequestBody UserSubscription userSubscription)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(userSubscriptionService.save(userSubscription));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserSubscription>updateUserSubscription(@RequestBody UserSubscription userSubscription, @PathVariable Integer id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(userSubscriptionService.edit(userSubscription,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserSubscription> deleteUserSubscription(@PathVariable Integer id)
    {
        userSubscriptionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
