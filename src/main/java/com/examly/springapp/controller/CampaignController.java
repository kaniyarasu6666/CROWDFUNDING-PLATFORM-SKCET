package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Campaign;
import com.examly.springapp.service.CampaignService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/campaigns")
@Validated
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    // Create Campaign
    @PostMapping
    public ResponseEntity<Campaign> createCampaign(@Valid @RequestBody Campaign campaign) {

        Campaign savedCampaign = campaignService.createCampaign(campaign);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCampaign);
    }

    // Get All Campaigns
    @GetMapping
    public ResponseEntity<List<Campaign>> getAllCampaigns(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String status) {

        List<Campaign> campaigns = campaignService.getAllCampaigns(category, status);

        return ResponseEntity.ok(campaigns);
    }

    // Get Campaign By Id
    @GetMapping("/{id}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable Long id) {

        Campaign campaign = campaignService.getCampaignById(id);

        return ResponseEntity.ok(campaign);
    }
}