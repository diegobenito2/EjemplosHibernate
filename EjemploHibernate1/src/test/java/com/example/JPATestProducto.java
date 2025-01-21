package com.example;

import com.example.model.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class JPATestProducto {
    @Test
    void create() {
        EntityManager entityManager = JpaUtil.getEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        Producto p1 = new Producto("Oiz", 2, BigDecimal.valueOf(5000), LocalDate.now(), "Bici Doble suspensión MTB");
        Producto p2 = new Producto("Trek Marlin 7", 5, BigDecimal.valueOf(750), LocalDate.now(), "Bici Hardtail MTB");
        Producto p3 = new Producto("Specialized Epic", 3, BigDecimal.valueOf(4200), LocalDate.now(), "Bici Doble suspensión MTB");
        Producto p4 = new Producto("Cannondale Scalpel", 4, BigDecimal.valueOf(5500), LocalDate.now(), "Bici Doble suspensión XC");
        Producto p5 = new Producto("Giant Talon 29", 6, BigDecimal.valueOf(600), LocalDate.now(), "Bici Hardtail MTB");
        Producto p6 = new Producto("BMC Teamelite 01", 2, BigDecimal.valueOf(3900), LocalDate.now(), "Bici Hardtail MTB");
        Producto p7 = new Producto("Santa Cruz Hightower", 1, BigDecimal.valueOf(6500), LocalDate.now(), "Bici Doble suspensión Trail");
        Producto p8 = new Producto("Scott Spark", 3, BigDecimal.valueOf(3200), LocalDate.now(), "Bici Doble suspensión XC");
        Producto p9 = new Producto("Merida One-Twenty", 4, BigDecimal.valueOf(4000), LocalDate.now(), "Bici Doble suspensión Trail");
        Producto p10 = new Producto("Orbea Alma", 5, BigDecimal.valueOf(1200), LocalDate.now(), "Bici Hardtail MTB");

        entityManager.persist(p1);
        entityManager.persist(p2);
        entityManager.persist(p3);
        entityManager.persist(p4);
        entityManager.persist(p5);
        entityManager.persist(p6);
        entityManager.persist(p7);
        entityManager.persist(p8);
        entityManager.persist(p9);
        entityManager.persist(p10);
        transaction.commit();
        entityManager.close();

    }

    @Test
    void retrieve() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Producto Bici=entityManager.find(Producto.class,"62247e56-4494-4c12-ae39-2a476e689479");
        System.out.println(Bici);
        transaction.commit();
        entityManager.close();
    }

    @Test
    void update() {
        EntityManager entityManager=JpaUtil.getEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        Producto bici=entityManager.find(Producto.class,"9fc1c307-edba-457b-bc2a-6f8a96795fe6");
        bici.setCantidad(7);
        bici.setFechaCreacion(LocalDate.parse("2024-12-31"));
        bici.setName("Scot Spark World Cup");
        bici.setPrecioUnitario(BigDecimal.valueOf(12000));
        entityTransaction.begin();
        entityManager.merge(bici);
        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void delete() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        entityTransaction.begin();
        Producto bici=entityManager.find(Producto.class,"4d5b3348-27af-4e3c-a01e-7204da672535");
        entityManager.remove(bici);
        entityTransaction.commit();


    }
}

