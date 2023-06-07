package com.global_solution.gs_api.services;

import com.global_solution.gs_api.models.Grao;
import com.global_solution.gs_api.models.TipoClima;
import com.global_solution.gs_api.models.TipoSolo;

import lombok.Data;

@Data
public class CultivosDTO {
    private Long id;
    private Grao tipoGrao;
    private TipoClima tipoClima;
    private TipoSolo tipoSolo;

    public void setParametrosIguais(boolean b) {
    }
}
