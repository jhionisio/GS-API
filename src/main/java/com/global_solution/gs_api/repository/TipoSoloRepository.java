package com.global_solution.gs_api.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.global_solution.gs_api.models.TipoSolo;

import jakarta.persistence.EntityManager;

public class TipoSoloRepository {

    private EntityManager entityManager;

    public TipoSoloRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Iterable<TipoSolo> findByLike(String tipoSolo) {
        String jpql = "SELECT d FROM TB_TIPO_SOLO d WHERE d.tipoSolo LIKE :tipoSolo";
        var query = entityManager.createQuery(jpql, TipoSolo.class)
                .setParameter("tipoSolo", "%" + tipoSolo + "%")
                .setHint("jakarta.persistence.query.timeout", 60000);
        var tipoSoloRes = query.getResultList();
        return tipoSoloRes;
    }

}
