package com.global_solution.gs_api.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.global_solution.gs_api.models.TipoSolo;

import jakarta.persistence.EntityManager;

public interface TipoSoloRepository extends JpaRepository<TipoSolo, Long> {

}
