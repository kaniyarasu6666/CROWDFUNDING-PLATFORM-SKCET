package com.examly.springapp.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Campaign;
import com.examly.springapp.model.CampaignStatus;
import com.examly.springapp.repository.CampaignRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    // Create Campaign
    public Campaign createCampaign(Campaign campaign) {

        campaign.setStatus(CampaignStatus.ACTIVE);

        if (campaign.getCurrentAmount() == null) {
            campaign.setCurrentAmount(BigDecimal.ZERO);
        }

        return campaignRepository.save(campaign);
    }

    // Get All Campaigns
    public List<Campaign> getAllCampaigns(String category, String status) {

        if (category != null && status != null) {
            return campaignRepository.findByCategoryAndStatus(
                    category,
                    CampaignStatus.valueOf(status));
        }

        if (category != null) {
            return campaignRepository.findByCategory(category);
        }

        if (status != null) {
            return campaignRepository.findByStatus(
                    CampaignStatus.valueOf(status));
        }

        return campaignRepository.findAll();
    }

    // Get Campaign By Id
    public Campaign getCampaignById(Long id) {

        Optional<Campaign> campaign = campaignRepository.findById(id);

        if (campaign.isEmpty()) {
            throw new EntityNotFoundException("Campaign not found");
        }

        updateCampaignStatusIfNeeded(campaign.get());

        return campaign.get();
    }

    // Update Campaign Status
    public void updateCampaignStatusIfNeeded(Campaign campaign) {

        if (campaign.getCurrentAmount() == null) {
            campaign.setCurrentAmount(BigDecimal.ZERO);
        }

        if (campaign.getCurrentAmount().compareTo(campaign.getGoalAmount()) >= 0) {
            campaign.setStatus(CampaignStatus.COMPLETED);
        } else {
            campaign.setStatus(CampaignStatus.ACTIVE);
        }

        campaignRepository.save(campaign);
    }
	 }