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

    public Iterable<TipoSolo> findByLike(String tipoSolo) {
        String jpql = "SELECT d FROM TB_TIPO_SOLO d WHERE d.tipoSolo LIKE :tipoSolo";
        var query = entityManager.createQuery(jpql, TipoSolo.class)
                .setParameter("tipoSolo", "%" + tipoSolo + "%")
                .setHint("jakarta.persistence.query.timeout", 60000);
        var tipoSoloRes = query.getResultList();
        return tipoSoloRes;
    }

    @Override
    public void createJPQL(TipoSolo tipoSolo) {

        repository.save(tipoSolo);

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
    public void updateJPQL(Long id, TipoSolo tipoSolo) {
        try {
            repository.findById(id);
        } catch (Exception e) {
            throw e;
        }
        tipoSolo.setID_TIPO_SOLO(id);
        repository.save(tipoSolo);
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
        repository.deleteById(id);
    }

}