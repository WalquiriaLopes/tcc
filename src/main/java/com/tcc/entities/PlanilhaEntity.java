package com.tcc.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "planilha")
public class PlanilhaEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "uf")
    private String uf;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "favorecido")
    private String favorecido;

    @Column(name = "cpf_cnpj")
    private String cpf_cnpj;

    @Column(name = "num_processo")
    private String num_processo;

    @Column(name = "num_port")
    private String num_port;

    @Column(name = "valor")
    private String valor;

    @Column(name = "nome_bloco")
    private String nomeBloco;

    @Column(name = "nome_grupo")
    private String nomeGrupo;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime localDateTime;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "data_buscada")
    private String dataBuscada;

    public String getDataBuscada() {
        return dataBuscada;
    }

    public void setDataBuscada(String dataBuscada) {
        this.dataBuscada = dataBuscada;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public String getNomeBloco() {
        return nomeBloco;
    }

    public void setNomeBloco(String nomeBloco) {
        this.nomeBloco = nomeBloco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getFavorecido() {
        return favorecido;
    }

    public void setFavorecido(String favorecido) {
        this.favorecido = favorecido;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getNum_processo() {
        return num_processo;
    }

    public void setNum_processo(String num_processo) {
        this.num_processo = num_processo;
    }

    public String getNum_port() {
        return num_port;
    }

    public void setNum_port(String num_port) {
        this.num_port = num_port;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
