package com.global_solution.gs_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global_solution.gs_api.models.Grao;

import jakarta.transaction.Transactional;

@Transactional
public interface GraoRepository extends JpaRepository<Grao, Long> {

};
