package com.global_solution.gs_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.global_solution.gs_api.models.Cultivos;

@Service
public interface CultivosService {
    Cultivos createJPQL(Cultivos cultivo);

    List<Cultivos> findByLikeJPQL(Integer graoId);

    void updateJPQL(Cultivos cultivo);

    void deleteJPQL(Cultivos cultivo);

    Cultivos findByIdJPQL(Long id);

    void deleteByIdJPQL(Long id);
}
