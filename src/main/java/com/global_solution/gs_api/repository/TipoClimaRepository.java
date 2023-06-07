package com.global_solution.gs_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.global_solution.gs_api.models.TipoClima;

import jakarta.persistence.EntityManager;

public class TipoClimaRepository {

    private EntityManager entityManager;

    public TipoClimaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Iterable<TipoClima> findByLike(String tipoClima) {
        String jpql = "SELECT d FROM TB_TIPO_CLIMA d WHERE d.tipoClima LIKE :tipoClima";
        var query = entityManager.createQuery(jpql, TipoClima.class)
                .setParameter("tipoClima", "%" + tipoClima + "%")
                .setHint("jakarta.persistence.query.timeout", 60000);
        var tipoClimaRes = query.getResultList();
        return tipoClimaRes;
    }

}
