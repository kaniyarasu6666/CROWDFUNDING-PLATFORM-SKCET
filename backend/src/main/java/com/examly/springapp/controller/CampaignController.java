package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Campaign;
import com.examly.springapp.service.CampaignService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/campaigns")
@Validated
@Tag(name = "Campaign Controller", description = "Campaign Management APIs")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    // Create Campaign
    @PostMapping
    @Operation(summary = "Create a new campaign")
    public ResponseEntity<Campaign> createCampaign(@Valid @RequestBody Campaign campaign) {

        Campaign savedCampaign = campaignService.createCampaign(campaign);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCampaign);
    }

    // Get All Campaigns
    @GetMapping
    @Operation(summary = "Get all campaigns")   
    public ResponseEntity<List<Campaign>> getAllCampaigns(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String status) {

        List<Campaign> campaigns = campaignService.getAllCampaigns(category, status);

        return ResponseEntity.ok(campaigns);
    }

    // Get Campaign By Id
    @GetMapping("/{id}")
    @Operation(summary = "Get campaign by ID")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable Long id) {

        Campaign campaign = campaignService.getCampaignById(id);

        return ResponseEntity.ok(campaign);
    }
}