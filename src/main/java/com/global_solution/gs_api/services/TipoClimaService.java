package com.global_solution.gs_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global_solution.gs_api.models.TipoClima;

@Service
public interface TipoClimaService {
    void createJPQL(TipoClima tipoClima);

    List<TipoClima> findByLikeJPQL(String tipoClima);

    List<TipoClima> findAllJPQL();

    void updateJPQL(Long id, TipoClima tipoClima);

    void deleteJPQL(TipoClima tipoClima);

    TipoClima findByIdJPQL(Long id);

    void deleteByIdJPQL(Long id);
}
