package com.global_solution.gs_api.controllers;

import jakarta.persistence.EntityManager;

public class CultivosController {
    private EntityManager entityManager;

    public void CultivosRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
