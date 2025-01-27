package com.example.model;

import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100)
    private String nombre;

    @Column(length = 100)
    @JoinColumn(unique = true)
    private String email;


    public Usuario() {
    }

    public Usuario(String email, String nombre) {
        this.email = email;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario: " +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'';
    }
}
