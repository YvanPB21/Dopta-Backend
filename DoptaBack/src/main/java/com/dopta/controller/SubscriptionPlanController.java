package com.dopta.controller;


import com.dopta.model.SubscriptionPlan;
import com.dopta.resource.subscriptionplan.SaveSubscriptionPlanResource;
import com.dopta.resource.subscriptionplan.SubscriptionPlanResource;
import com.dopta.service.SubscriptionPlanService;
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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
public class SubscriptionPlanController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    @Operation(summary = "Get Subscription Plans", description = "Get all subscription plans by Pages", tags = {"subscriptionplans"})
    @GetMapping("/subscriptionplans")
    public List<SubscriptionPlanResource> getAllSubscriptionPlans(@Parameter(description = "Pageable parameter") Pageable pageable) {
        Page<SubscriptionPlan> subscriptionPlanPage = subscriptionPlanService.getAllSubscriptionPlans(pageable);
        List<SubscriptionPlanResource> resources = subscriptionPlanPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return resources;
    }

    @Operation(summary = "Get SubscriptionPlan by Id", description = "Get a subscriptionPlan by specifying Id", tags = {"subscriptionplans"})
    @GetMapping("/subscriptionplans/{subscriptionPlanId}")
    public SubscriptionPlanResource getSubscriptionPlanById(
            @Parameter(description = "SubscriptionPlan Id")
            @PathVariable(name = "subscriptionPlanId") Integer subscriptionPlanId) {
        return convertToResource(subscriptionPlanService.getSubscriptionPlanById(subscriptionPlanId));
    }

    @PostMapping("/subscriptionplans")
    public SubscriptionPlanResource createSubscriptionPlan(@Valid @RequestBody SaveSubscriptionPlanResource resource) {
        SubscriptionPlan subscriptionPlan = convertToEntity(resource);
        return convertToResource(subscriptionPlanService.createSubscriptionPlan(subscriptionPlan));
    }

    @PutMapping("/subscriptionplans/{subscriptionPlanId}")
    public SubscriptionPlanResource updateSubscriptionPlan(@PathVariable(name = "subscriptionPlanId") Integer subscriptionPlanId,
                                                           @Valid @RequestBody SaveSubscriptionPlanResource resource) {
        SubscriptionPlan subscriptionPlan = convertToEntity(resource);
        return convertToResource(subscriptionPlanService.editSubscriptionPlan(subscriptionPlan, subscriptionPlanId));
    }

    @DeleteMapping("/subscriptionplans/{subscriptionPlanId}")
    public ResponseEntity<?> deleteSubscriptionPlan(@PathVariable(name = "subscriptionPlanId") Integer subscriptionPlanId) {
        return subscriptionPlanService.deleteSubscriptionPlanById(subscriptionPlanId);
    }

    private SubscriptionPlanResource convertToResource(SubscriptionPlan entity) {
        return mapper.map(entity, SubscriptionPlanResource.class);
    }

    private SubscriptionPlan convertToEntity(SaveSubscriptionPlanResource resource) {
        return mapper.map(resource, SubscriptionPlan.class);
    }

}
