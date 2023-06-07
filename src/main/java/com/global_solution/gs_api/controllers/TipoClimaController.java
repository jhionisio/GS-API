package com.global_solution.gs_api.controllers;

import jakarta.persistence.EntityManager;

public class TipoClimaController {
    private EntityManager entityManager;

    public void TipoClimaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
