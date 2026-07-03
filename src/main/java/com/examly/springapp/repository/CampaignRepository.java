package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Campaign;
import com.examly.springapp.model.CampaignStatus;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    List<Campaign> findByCategory(String category);

    List<Campaign> findByStatus(CampaignStatus status);

    List<Campaign> findByCategoryAndStatus(String category, CampaignStatus status);

}