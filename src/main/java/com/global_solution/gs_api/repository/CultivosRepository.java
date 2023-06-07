package com.global_solution.gs_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global_solution.gs_api.models.Cultivos;

import jakarta.persistence.EntityManager;

public interface CultivosRepository extends JpaRepository<Cultivos, Long> {

}
