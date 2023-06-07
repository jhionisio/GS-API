package com.global_solution.gs_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.global_solution.gs_api.models.Grao;
import com.global_solution.gs_api.repository.GraoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class GraoServiceImpl implements GraoService {

    @Autowired // IoD IoC
    GraoRepository repository;

    private EntityManager entityManager;

    public GraoServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void createJPQL(Grao gra) {
        try {
            entityManager.persist(gra);
        } catch (Exception e) {
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
    public void updateJPQL(Long id, Grao gra) {
        try {
            repository.findById(id);
        } catch (Exception e) {
            throw e;
        }
        gra.setID_GRAO(id);
        repository.save(gra);
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
        repository.deleteById(id);
    }

}
