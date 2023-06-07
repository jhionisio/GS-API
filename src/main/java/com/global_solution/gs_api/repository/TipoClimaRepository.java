package com.global_solution.gs_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.global_solution.gs_api.models.TipoClima;

import jakarta.persistence.EntityManager;

public interface TipoClimaRepository extends JpaRepository<TipoClima, Long> {
}
