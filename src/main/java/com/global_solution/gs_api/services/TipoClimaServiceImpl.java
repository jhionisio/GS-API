package com.global_solution.gs_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.global_solution.gs_api.models.TipoClima;
import com.global_solution.gs_api.repository.TipoClimaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Service
public class TipoClimaServiceImpl implements TipoClimaService {

    @Autowired // IoD IoC
    TipoClimaRepository repository;

    private EntityManager entityManager;

    public TipoClimaServiceImpl(EntityManager entityManager) {
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

    @Override
    public void createJPQL(TipoClima tipoClima) {

        try {

            entityManager.getTransaction().begin();

            entityManager.persist(tipoClima);

            entityManager.getTransaction().commit();

        } catch (Exception e) {

            entityManager.getTransaction().rollback();

            throw e;

        }

    }

    @Override
    public List<TipoClima> findByLikeJPQL(String tipoClima) {
        String jpql = "SELECT d FROM TB_TIPO_CLIMA d WHERE d.tipo_clima LIKE :clima";
        TypedQuery<TipoClima> query = entityManager.createQuery(jpql, TipoClima.class)
                .setParameter("clima", "%" + tipoClima + "%")
                .setHint("jakarta.persistence.query.timeout", 60000);
        List<TipoClima> tipoClimas = query.getResultList();
        return tipoClimas;
    }

    @Override
    public void updateJPQL(TipoClima tipoClima) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(tipoClima);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void deleteJPQL(TipoClima tipoClima) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(tipoClima);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public TipoClima findByIdJPQL(Long id) {
        TipoClima tipoClima = entityManager.find(TipoClima.class, id);
        if (tipoClima == null) {
            return null;
        }
        return tipoClima;
    }

    @Override
    public void deleteByIdJPQL(Long id) {
        entityManager.getTransaction().begin();
        try {
            TipoClima tipoClima = entityManager.find(TipoClima.class, id);
            if (tipoClima != null) {
                entityManager.remove(tipoClima);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

}
