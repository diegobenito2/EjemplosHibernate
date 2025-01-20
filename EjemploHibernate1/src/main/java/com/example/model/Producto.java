package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

/*CREATE TABLE Producto (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nombre VARCHAR(100) NOT NULL,
cantidad INT NOT NULL,
precioUnitario DECIMAL(10, 2) NOT NULL,
fechaCreacion DATE NOT NULL,
descripcion VARCHAR(255)
);*/
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 100)
    private String name;
    @Column(nullable = false)
    private int cantidad;
    @Column(nullable = false,precision = 10,scale = 2)
    private double precioUnitario;
    @Column(nullable = false)
    private LocalDate fechaCreacion;
    @Column(length = 255)
    private String descripcion;

    public Producto() {
    }

    public Producto(Long id, String name, int cantidad, double precioUnitario, LocalDate fechaCreacion, String descripcion) {
        this.id = id;
        this.name = name;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", fechaCreacion=" + fechaCreacion +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
