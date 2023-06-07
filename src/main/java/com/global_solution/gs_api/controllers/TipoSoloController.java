package com.global_solution.gs_api.controllers;

import jakarta.persistence.EntityManager;

public class TipoSoloController {
    private EntityManager entityManager;

    public void TipoSoloRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
