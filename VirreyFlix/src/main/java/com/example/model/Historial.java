package com.example.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Historial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date fecha_reproduccion;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "episodio_id")
    private Episodio episodio;

    public Historial(Date fecha_reproduccion, Perfil perfil, Episodio episodio) {
        this.fecha_reproduccion = fecha_reproduccion;
        this.perfil = perfil;
        this.episodio = episodio;
    }

    public Historial() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha_reproduccion() {
        return fecha_reproduccion;
    }

    public void setFecha_reproduccion(Date fecha_reproduccion) {
        this.fecha_reproduccion = fecha_reproduccion;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Episodio getSerie() {
        return episodio;
    }

    public void setSerie(Episodio serie) {
        this.episodio = serie;
    }

    @Override
    public String toString() {
        return "Historial: " +
                "id=" + id +
                ", fecha_reproduccion=" + fecha_reproduccion +
                ", perfil=" + perfil +
                ", episodio=" + episodio;
    }
}
