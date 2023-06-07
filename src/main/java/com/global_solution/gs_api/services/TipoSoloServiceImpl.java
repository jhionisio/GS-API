package com.global_solution.gs_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.global_solution.gs_api.models.TipoSolo;
import com.global_solution.gs_api.repository.TipoSoloRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Service
public class TipoSoloServiceImpl implements TipoSoloService {

    @Autowired // IoD IoC
    TipoSoloRepository repository;

    private EntityManager entityManager;

    public TipoSoloServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createJPQL(TipoSolo tipoSolo) {

        try {

            entityManager.getTransaction().begin();

            entityManager.persist(tipoSolo);

            entityManager.getTransaction().commit();

        } catch (Exception e) {

            entityManager.getTransaction().rollback();

            throw e;

        }

    }

    @Override
    public List<TipoSolo> findByLikeJPQL(String tipoSolo) {
        String jpql = "SELECT d FROM TB_TIPO_SOLO d WHERE d.tipo_solo LIKE :solo";
        TypedQuery<TipoSolo> query = entityManager.createQuery(jpql, TipoSolo.class)
                .setParameter("solo", "%" + tipoSolo + "%")
                .setHint("jakarta.persistence.query.timeout", 60000);
        List<TipoSolo> tipoSolos = query.getResultList();
        return tipoSolos;
    }

    @Override
    public void updateJPQL(TipoSolo tipoSolo) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(tipoSolo);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void deleteJPQL(TipoSolo tipoSolo) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(tipoSolo);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public TipoSolo findByIdJPQL(Long id) {
        TipoSolo tipoSolo = entityManager.find(TipoSolo.class, id);
        if (tipoSolo == null) {
            return null;
        }
        return tipoSolo;
    }

    @Override
    public void deleteByIdJPQL(Long id) {
        entityManager.getTransaction().begin();
        try {
            TipoSolo tipoSolo = entityManager.find(TipoSolo.class, id);
            if (tipoSolo != null) {
                entityManager.remove(tipoSolo);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

}