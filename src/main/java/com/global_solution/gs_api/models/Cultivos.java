package com.global_solution.gs_api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@AllArgsConstructor
@Builder
@Table(name = "TB_CULTIVOS")
public class Cultivos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ID_GRAO")
    private Grao grao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_CLIMA")
    private TipoClima tipoClima;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_SOLO")
    private TipoSolo tipoSolo;
}
