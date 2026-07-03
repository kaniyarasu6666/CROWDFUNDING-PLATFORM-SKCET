package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Donation;
import com.examly.springapp.service.DonationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/campaigns")
@Validated
public class DonationController {

    @Autowired
    private DonationService donationService;

    // Make Donation
    @PostMapping("/{campaignId}/donations")
    public ResponseEntity<Donation> makeDonation(
            @PathVariable Long campaignId,
            @Valid @RequestBody Donation donation) {

        Donation savedDonation = donationService.makeDonation(campaignId, donation);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedDonation);
    }

    // Get Donations for a Campaign
    @GetMapping("/{campaignId}/donations")
    public ResponseEntity<List<Donation>> getDonationsForCampaign(
            @PathVariable Long campaignId) {

        List<Donation> donations = donationService.getDonationsForCampaign(campaignId);

        return ResponseEntity.ok(donations);
    }
}