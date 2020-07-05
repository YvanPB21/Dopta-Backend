package com.dopta.controller;

import com.dopta.model.UserSubscription;
import com.dopta.resource.usersubscription.SaveUserSubscriptionResource;
import com.dopta.resource.usersubscription.UserSubscriptionResource;
import com.dopta.service.UserSubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "https://dopta.netlify.app")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserSubscriptionController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserSubscriptionService userSubscriptionService;

    @Operation(summary = "Get User Subscriptions", description = "Get all user subscriptions by Pages", tags = {"usersubscriptions"})
    @GetMapping("/usersubscriptions")
    public List<UserSubscriptionResource> getAllUserSubscriptions(@Parameter(description = "Pageable parameter") Pageable pageable) {
        Page<UserSubscription> userSubscriptionPage = userSubscriptionService.getAllSubscriptions(pageable);
        List<UserSubscriptionResource> resources = userSubscriptionPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return resources;
    }

    @Operation(summary = "Get User Subscription by Id", description = "Get a user subscription by specifying Id", tags = {"usersubscriptions"})
    @GetMapping("/usersubscriptions/{userSubscriptionId}")
    public UserSubscriptionResource getUserSubscriptionById(
            @Parameter(description = "UserSubscription Id")
            @PathVariable(name = "userSubscriptionId") Integer userSubscriptionId) {
        return convertToResource(userSubscriptionService.getUserSubscriptionById(userSubscriptionId));
    }

    @Operation(summary = "Get User Subscriptions by user Id", description = "Get user subscriptions by specifying User Id", tags = {"usersubscriptions"})
    @GetMapping("/usersubscriptions/user/{userId}")
    public List<UserSubscriptionResource> getUserSubscriptionsByUserId(
            @Parameter(description = "Pageable parameter") Pageable pageable,
            @Parameter(description = "User Id")
            @PathVariable(name = "userId") Integer userId) {
        Page<UserSubscription> userSubscriptionPage = userSubscriptionService.getUserSubscriptionByUserId(userId, pageable);
        List<UserSubscriptionResource> resources = userSubscriptionPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return resources;
    }

    @Operation(summary = "Get UserSubscriptions by Subscription Plan Id", description = "Get user subscriptions by specifying subscription plan Id", tags = {"usersubscriptions"})
    @GetMapping("/usersubscriptions/subscriptionplan/{subscriptionPlanId}")
    public List<UserSubscriptionResource> getUserSubscriptionsBySubscriptionPlanId(
            @Parameter(description = "Pageable parameter") Pageable pageable,
            @Parameter(description = "SubscriptionPlan Id")
            @PathVariable(name = "subscriptionPlanId") Integer subscriptionPlanId) {
        Page<UserSubscription> userSubscriptionPage = userSubscriptionService.getUserSubscriptionBySubscriptionPlanId(subscriptionPlanId, pageable);
        List<UserSubscriptionResource> resources = userSubscriptionPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return resources;
    }

    @Operation(summary = "Get User Subscriptions by user Id and Subscription Plan Id", description = "Get user subscriptions by specifying User Id and Subscription Plan Id", tags = {"usersubscriptions"})
    @GetMapping(value = {"/usersubscriptions/user/{userId}/subscriptionplan/{subscriptionPlanId}", "/usersubscriptions/subscriptionplan/{subscriptionPlanId}/user/{userId}"})
    public List<UserSubscriptionResource> getUserSubscriptionsByUserIdAndSubscriptionPlanId(
            @Parameter(description = "Pageable parameter") Pageable pageable,
            @Parameter(description = "User Id") @PathVariable(name = "userId") Integer userId,
            @Parameter(description = "SubscriptionPlan Id") @PathVariable(name = "subscriptionPlanId") Integer subscriptionPlanId
    ) {
        Page<UserSubscription> userSubscriptionPage = userSubscriptionService.getUserSubscriptionByUserIdAndSubscriptionPlanId(userId, subscriptionPlanId, pageable);
        List<UserSubscriptionResource> resources = userSubscriptionPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return resources;
    }

    @PostMapping("/usersubscriptions")
    public UserSubscriptionResource createUserSubscription(@Valid @RequestBody SaveUserSubscriptionResource resource, Integer userId, Integer subscriptionPlanId) {
        UserSubscription userSubscription = convertToEntity(resource);
        return convertToResource(userSubscriptionService.createUserSubscription(userSubscription, userId, subscriptionPlanId));
    }

    @PutMapping("/usersubscriptions/{userSubscriptionId}")
    public UserSubscriptionResource updateUserSubscription(@PathVariable(name = "userSubscriptionId") Integer userSubscriptionId,
                                                           @Valid @RequestBody SaveUserSubscriptionResource resource, Integer userId, Integer subscriptionPlanId) {
        UserSubscription userSubscription = convertToEntity(resource);
        return convertToResource(userSubscriptionService.editUserSubscription(userSubscription, userSubscriptionId, userId, subscriptionPlanId));
    }

    @DeleteMapping("/usersubscriptions/{userSubscriptionId}")
    public ResponseEntity<?> deleteUserSubscription(@PathVariable(name = "userSubscriptionId") Integer userSubscriptionId) {
        return userSubscriptionService.deleteUserSubscriptionById(userSubscriptionId);
    }

    /*SubsPlan, User*/
    private UserSubscriptionResource convertToResource(UserSubscription entity) {
        return mapper.map(entity, UserSubscriptionResource.class);
    }

    private UserSubscription convertToEntity(SaveUserSubscriptionResource resource) {
        return mapper.map(resource, UserSubscription.class);
    }

}
