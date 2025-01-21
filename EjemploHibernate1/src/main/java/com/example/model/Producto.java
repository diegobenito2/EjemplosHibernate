package com.example.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;

/*CREATE TABLE Producto (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nombre VARCHAR(100) NOT NULL,
cantidad INT NOT NULL,
precioUnitario DECIMAL(10, 2) NOT NULL,
fechaCreacion DATE NOT NULL,
descripcion VARCHAR(255)
);*/
@Entity
public class Producto {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    @Column(columnDefinition = "CHAR(36)")
    private String id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private int cantidad;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;
    @Column(nullable = false)
    private LocalDate fechaCreacion;
    @Column(length = 255)
    private String descripcion;

    public Producto() {
    }

    public Producto( String name, int cantidad, BigDecimal precioUnitario, LocalDate fechaCreacion, String descripcion) {
        this.name = name;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
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
