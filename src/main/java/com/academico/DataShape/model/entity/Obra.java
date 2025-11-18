package com.academico.DataShape.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "obras_tb")
public class Obra {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "titulo_obra", unique = true)
    private String tituloObra;

    @Column(nullable = false, unique = true)
    private String tituloNormalizado;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloObra() {
        return tituloObra;
    }
    public void setTituloObra(String tituloObra) {
        this.tituloObra = tituloObra;
    }

    public String getTituloNormalizado() {
        return tituloNormalizado;
    }
    public void setTituloNormalizado(String tituloNormalizado) {
        this.tituloNormalizado = tituloNormalizado;
    }


    
}
