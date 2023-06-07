package com.global_solution.gs_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global_solution.gs_api.models.Grao;

@Service
public interface GraoService {
    void createJPQL(Grao gra);

    List<Grao> findByLikeJPQL(String grao);

    void updateJPQL(Long id, Grao gra);

    Grao findByIdJPQL(Long id);

    void deleteByIdJPQL(Long id);
}
