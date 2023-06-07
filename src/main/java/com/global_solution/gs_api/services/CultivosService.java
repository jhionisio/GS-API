package com.global_solution.gs_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.global_solution.gs_api.models.Cultivos;

@Service
public interface CultivosService {
    Cultivos createJPQL(Cultivos cultivo);

    List<CultivosViewDto> findByDsClimaAndNmTipoSolo(Integer clima, Integer solo);

    List<Cultivos> findByLikeJPQL(Integer graoId);

    List<CultivosViewDto> findAllJPQL();

    void updateJPQL(Cultivos cultivo);

    void deleteJPQL(Cultivos cultivo);

    CultivosViewDto findByIdJPQL(Long id);

    void deleteByIdJPQL(Long id);
}
