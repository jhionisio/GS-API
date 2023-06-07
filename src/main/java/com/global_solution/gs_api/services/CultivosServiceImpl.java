package com.global_solution.gs_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import com.global_solution.gs_api.models.Cultivos;
import com.global_solution.gs_api.models.Grao;
import com.global_solution.gs_api.models.TipoClima;
import com.global_solution.gs_api.models.TipoSolo;
import com.global_solution.gs_api.repository.CultivosRepository;
import com.global_solution.gs_api.repository.GraoRepository;
import com.global_solution.gs_api.repository.TipoClimaRepository;
import com.global_solution.gs_api.repository.TipoSoloRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Service
public class CultivosServiceImpl implements CultivosService {

    @Autowired // IoD IoC
    CultivosRepository repository;

    @Autowired // IoD IoC
    TipoClimaRepository tipoClimaRepository;

    @Autowired // IoD IoC
    TipoSoloRepository tipoSoloRepository;

    @Autowired // IoD IoC
    GraoRepository graoRepository;

    private EntityManager entityManager;

    public CultivosServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CultivosViewDto> findByDsClimaAndNmTipoSolo(Integer dsClima, Integer nmTipoSolo) {
        List<Cultivos> cultivosList = repository.findAll();
        List<CultivosViewDto> cultivosViewList = new ArrayList<>();

        for (Cultivos cultivos : cultivosList) {
            Grao grao = cultivos.getGrao();
            TipoClima tipoClima = cultivos.getTipoClima();
            TipoSolo tipoSolo = cultivos.getTipoSolo();

            if (dsClima != null && nmTipoSolo != null && tipoClima.getID_CLIMA().equals(dsClima)
                    && tipoSolo.getID_TIPO_SOLO().equals(nmTipoSolo)) {
                CultivosViewDto cultivosView = new CultivosViewDto();
                cultivosView.setId(cultivos.getID());
                cultivosView.setGraoId(grao != null ? grao.getID_GRAO() : null);
                cultivosView.setGraoNome(grao != null ? grao.getNM_GRAO() : null);
                cultivosView.setTipoClimaId(tipoClima.getID_CLIMA() != null ? tipoClima.getID_CLIMA() : null);
                cultivosView.setTipoClimaDescricao(tipoClima != null ? tipoClima.getDS_CLIMA() : null);
                cultivosView.setTipoSoloId(tipoSolo.getID_TIPO_SOLO() != null ? tipoSolo.getID_TIPO_SOLO() : null);
                cultivosView.setTipoSoloNome(tipoSolo != null ? tipoSolo.getNM_TIPO_SOLO() : null);

                cultivosViewList.add(cultivosView);
            }
        }

        return cultivosViewList;
    }

    @Override
    public Cultivos createJPQL(Cultivos cultivos) {
        Grao grao = cultivos.getGrao();
        TipoClima tipoClima = cultivos.getTipoClima();
        TipoSolo tipoSolo = cultivos.getTipoSolo();

        graoRepository.save(grao);
        tipoClimaRepository.save(tipoClima);
        tipoSoloRepository.save(tipoSolo);

        return repository.save(cultivos);
    }

    @Override
    public List<Cultivos> findByLikeJPQL(Integer graoId) {
        String jpql = "SELECT d FROM TB_CULTIVOS d WHERE d.grao LIKE :cultivo";
        TypedQuery<Cultivos> query = entityManager.createQuery(jpql, Cultivos.class)
                .setParameter("cultivo", "%" + graoId + "%")
                .setHint("jakarta.persistence.query.timeout", 60000);
        List<Cultivos> cultivo = query.getResultList();
        return cultivo;
    }

    @Override
    public void updateJPQL(Cultivos cultivo) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(cultivo);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void deleteJPQL(Cultivos cultivo) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(cultivo);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public CultivosViewDto findByIdJPQL(Long id) {
        Cultivos cultivos = repository.findById(id).orElse(null);
        if (cultivos == null) {
            return null;
        }

        Grao grao = cultivos.getGrao();
        TipoClima tipoClima = cultivos.getTipoClima();
        TipoSolo tipoSolo = cultivos.getTipoSolo();

        CultivosViewDto cultivosView = new CultivosViewDto();
        cultivosView.setId(cultivos.getID());
        cultivosView.setGraoId(grao != null ? grao.getID_GRAO() : null);
        cultivosView.setGraoNome(grao != null ? grao.getNM_GRAO() : null);
        cultivosView.setTipoClimaId(tipoClima != null ? tipoClima.getID_CLIMA() : null);
        cultivosView.setTipoClimaDescricao(tipoClima != null ? tipoClima.getDS_CLIMA() : null);
        cultivosView.setTipoSoloId(tipoSolo != null ? tipoSolo.getID_TIPO_SOLO() : null);
        cultivosView.setTipoSoloNome(tipoSolo != null ? tipoSolo.getNM_TIPO_SOLO() : null);

        return cultivosView;
    }

    @Override
    public void deleteByIdJPQL(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<CultivosViewDto> findAllJPQL() {
        List<Cultivos> cultivosList = repository.findAll();
        List<CultivosViewDto> cultivosViewList = new ArrayList<>();

        for (Cultivos cultivos : cultivosList) {
            Grao grao = cultivos.getGrao();
            TipoClima tipoClima = cultivos.getTipoClima();
            TipoSolo tipoSolo = cultivos.getTipoSolo();

            CultivosViewDto cultivosView = new CultivosViewDto();
            cultivosView.setId(cultivos.getID());
            cultivosView.setGraoId(grao != null ? grao.getID_GRAO() : null);
            cultivosView.setGraoNome(grao != null ? grao.getNM_GRAO() : null);
            cultivosView.setTipoClimaId(tipoClima != null ? tipoClima.getID_CLIMA() : null);
            cultivosView.setTipoClimaDescricao(tipoClima != null ? tipoClima.getDS_CLIMA() : null);
            cultivosView.setTipoSoloId(tipoSolo != null ? tipoSolo.getID_TIPO_SOLO() : null);
            cultivosView.setTipoSoloNome(tipoSolo != null ? tipoSolo.getNM_TIPO_SOLO() : null);

            cultivosViewList.add(cultivosView);
        }

        return cultivosViewList;
    }

}