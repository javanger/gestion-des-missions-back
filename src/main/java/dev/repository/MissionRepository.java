package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission, Integer> {
}
