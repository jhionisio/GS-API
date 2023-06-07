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

    public List<TipoClima> findByLikeJPQL(String tipoClima) {
        System.out.println(tipoClima);
        String jpql = "SELECT tc FROM TipoClima tc WHERE tc.DS_CLIMA LIKE CONCAT('%', :tipoClima, '%')";
        TypedQuery<TipoClima> query = entityManager.createQuery(jpql, TipoClima.class)
                .setParameter("tipoClima", tipoClima);
        return query.getResultList();
    }

    @Override
    public void createJPQL(TipoClima tipoClima) {
        repository.save(tipoClima);
    }

    @Override
    public List<TipoClima> findAllJPQL() {
        TypedQuery<TipoClima> query = entityManager.createQuery("SELECT tc FROM TipoClima tc", TipoClima.class);
        return query.getResultList();
    }

    @Override
    public void updateJPQL(Long id, TipoClima tipoClima) {
        try {
            repository.findById(id);
        } catch (Exception e) {
            throw e;
        }
        tipoClima.setID_CLIMA(id);
        repository.save(tipoClima);
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
        repository.deleteById(id);
    }

}
