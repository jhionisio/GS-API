package com.global_solution.gs_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.global_solution.gs_api.models.Grao;
import com.global_solution.gs_api.repository.GraoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Service
public class GraoServiceImpl implements GraoService {

    @Autowired // IoD IoC
    GraoRepository repository;

    private EntityManager entityManager;

    public GraoServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createJPQL(Grao gra) {

        try {

            entityManager.getTransaction().begin();

            entityManager.persist(gra);

            entityManager.getTransaction().commit();

        } catch (Exception e) {

            entityManager.getTransaction().rollback();

            throw e;

        }

    }

    @Override
    public List<Grao> findByLikeJPQL(String grao) {
        String jpql = "SELECT d FROM TB_GRAO d WHERE d.grao LIKE :grao";
        TypedQuery<Grao> query = entityManager.createQuery(jpql, Grao.class)
                .setParameter("grao", "%" + grao + "%")
                .setHint("jakarta.persistence.query.timeout", 60000);
        List<Grao> graos = query.getResultList();
        return graos;
    }

    @Override
    public void updateJPQL(Grao gra) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(gra);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void deleteJPQL(Grao gra) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(gra);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Grao findByIdJPQL(Long id) {
        Grao gra = entityManager.find(Grao.class, id);
        if (gra == null) {
            return null;
        }
        return gra;
    }

    @Override
    public void deleteByIdJPQL(Long id) {
        entityManager.getTransaction().begin();
        try {
            Grao gra = entityManager.find(Grao.class, id);
            if (gra != null) {
                entityManager.remove(gra);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

}
