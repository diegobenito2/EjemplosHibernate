package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 200)
    private String titulo;

    @Column(length = 200)
    private String genero;

    private int calificacion_edad;

    public Serie() {
    }

    public Serie(String titulo, String genero, int calificacion_edad) {
        this.titulo = titulo;
        this.genero = genero;
        this.calificacion_edad = calificacion_edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getCalificacion_edad() {
        return calificacion_edad;
    }

    public void setCalificacion_edad(int calificacion_edad) {
        this.calificacion_edad = calificacion_edad;
    }
}
