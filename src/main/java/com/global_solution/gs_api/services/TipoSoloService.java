package com.global_solution.gs_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global_solution.gs_api.models.TipoSolo;

@Service
public interface TipoSoloService {
    void createJPQL(TipoSolo tipoSolo);

    List<TipoSolo> findByLikeJPQL(String tipoSolo);

    void updateJPQL(Long id, TipoSolo tipoSolo);

    void deleteJPQL(TipoSolo tipoSolo);

    TipoSolo findByIdJPQL(Long id);

    void deleteByIdJPQL(Long id);
}
