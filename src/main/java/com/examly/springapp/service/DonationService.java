package com.examly.springapp.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Campaign;
import com.examly.springapp.model.CampaignStatus;
import com.examly.springapp.model.Donation;
import com.examly.springapp.repository.CampaignRepository;
import com.examly.springapp.repository.DonationRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    // Make Donation
    public Donation makeDonation(Long campaignId, Donation donation) {

        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new EntityNotFoundException("Campaign not found"));

        // Check deadline only if it is available
        if (campaign.getDeadline() != null &&
                campaign.getDeadline().isBefore(LocalDate.now())) {
            campaign.setStatus(CampaignStatus.EXPIRED);
        }

        // Set default status
        if (campaign.getStatus() == null) {
            campaign.setStatus(CampaignStatus.ACTIVE);
        }

        // Allow donation only for ACTIVE campaigns
        if (campaign.getStatus() != CampaignStatus.ACTIVE) {
            throw new ValidationException("Campaign is not ACTIVE");
        }

        // Initialize current amount
        if (campaign.getCurrentAmount() == null) {
            campaign.setCurrentAmount(BigDecimal.ZERO);
        }

        // Update current amount
        campaign.setCurrentAmount(
                campaign.getCurrentAmount().add(donation.getAmount()));

        // Mark completed if goal reached
        if (campaign.getCurrentAmount().compareTo(campaign.getGoalAmount()) >= 0) {
            campaign.setStatus(CampaignStatus.COMPLETED);
        }

        donation.setCampaign(campaign);

        campaignRepository.save(campaign);

        return donationRepository.save(donation);
    }
    // Get Donations
 public List<Donation> getDonationsForCampaign(Long campaignId) {

 if (!campaignRepository.existsById(campaignId)) {
 throw new EntityNotFoundException("Campaign not found");
 }

 return donationRepository.findByCampaignId(campaignId);
 }
}