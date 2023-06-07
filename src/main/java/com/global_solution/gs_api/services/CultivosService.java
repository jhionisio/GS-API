package com.global_solution.gs_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global_solution.gs_api.models.Cultivos;

@Service
public interface CultivosService {
    void createJPQL(Cultivos cultivo);

    List<Cultivos> findByLikeJPQL(String cultivos);

    void updateJPQL(Cultivos cultivo);

    void deleteJPQL(Cultivos cultivo);

    Cultivos findByIdJPQL(Long id);

    void deleteByIdJPQL(Long id);
}
