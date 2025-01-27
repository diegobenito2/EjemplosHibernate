package com.example;


import com.example.model.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/*
CRUD:
- Create
- Retrieve
- Update
- Delete
 */
public class ProductoTest {

    @Test
    void create() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

//        Producto p1 = new Producto("Terra", 2, BigDecimal.valueOf(2000), LocalDate.parse("2025-01-20"), "");
        Producto p2 = new Producto("Oiz", 2, BigDecimal.valueOf(5000), LocalDate.now(), "Bici Doble suspension MTB");

//        session.persist(p1);
        session.persist(p2);
        tx.commit();
        session.close();
    }

    @Test
    void retrieve() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();


        Producto producto1FromDb = session.find(Producto.class, "45d0bc8d-25c7-4998-96a1-dcc45a3a6622");

        tx.commit();
        System.out.println(producto1FromDb);
        session.close();

    }


    @Test
    void update() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Producto p1 = new Producto("Terra", 2, BigDecimal.valueOf(2000), LocalDate.now(), "");

        Transaction tx = session.beginTransaction();
        session.merge(p1);
        tx.commit();

        System.out.println(p1);
        session.close();
    }

    @Test
    void delete() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Producto p1 = new Producto("Terra", 2, BigDecimal.valueOf(2000), LocalDate.now(), "");


        session.remove(p1);
        tx.commit();

        System.out.println(p1);
        session.close();
    }

    @Test
    void muestraProducto() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Producto producto = session.find(Producto.class, "5da3ca51-cfcc-4d53-94ca-39c35e845bdd");
        tx.commit();
        System.out.println(producto);
        session.close();
    }

    @Test
    void mostrarProductos() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Producto> productos = session.createQuery("from Producto", Producto.class).list();

        for (Producto p : productos) {
            System.out.println(p);
        }
        session.close();
    }
}
