package com.example.demo.repository;

import com.example.demo.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
    /*Optional<List<Campaign>> findByName(String name);*/
}
