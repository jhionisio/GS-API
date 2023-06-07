package com.global_solution.gs_api.services;

public class CultivosViewDto {
    private Long id;
    private Long graoId;
    private String graoNome;
    private Long tipoClimaId;
    private String tipoClimaDescricao;
    private Long tipoSoloId;
    private String tipoSoloNome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGraoId() {
        return graoId;
    }

    public void setGraoId(Long graoId) {
        this.graoId = graoId;
    }

    public String getGraoNome() {
        return graoNome;
    }

    public void setGraoNome(String graoNome) {
        this.graoNome = graoNome;
    }

    public Long getTipoClimaId() {
        return tipoClimaId;
    }

    public void setTipoClimaId(Long tipoClimaId) {
        this.tipoClimaId = tipoClimaId;
    }

    public String getTipoClimaDescricao() {
        return tipoClimaDescricao;
    }

    public void setTipoClimaDescricao(String tipoClimaDescricao) {
        this.tipoClimaDescricao = tipoClimaDescricao;
    }

    public Long getTipoSoloId() {
        return tipoSoloId;
    }

    public void setTipoSoloId(Long tipoSoloId) {
        this.tipoSoloId = tipoSoloId;
    }

    public String getTipoSoloNome() {
        return tipoSoloNome;
    }

    public void setTipoSoloNome(String tipoSoloNome) {
        this.tipoSoloNome = tipoSoloNome;
    }
}
