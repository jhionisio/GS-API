package com.global_solution.gs_api.repository;

import jakarta.persistence.EntityManager;

public class CultivosRepository {

    private EntityManager entityManager;

    public CultivosRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
