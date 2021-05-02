package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.IntegrationEntity;
import java.util.Optional;

public interface IntegrationRepository extends JpaRepository <IntegrationEntity, Long> {
	Optional<IntegrationEntity> findByName(String username);
}
