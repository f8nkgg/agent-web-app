package com.example.agent.repository;

import com.example.agent.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findAllByAgentId(Long agentId);
}
