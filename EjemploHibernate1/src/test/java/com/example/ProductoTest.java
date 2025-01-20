package com.example;

import com.example.model.Employee;
import com.example.model.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/*
CRUD:
- Create
- Retrieve
- Update
- Delete
 */
public class ProductoTest {

    @Test
    void persist() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        Producto producto1 = new Producto();

        session.persist(producto1);
        tx.commit();

        session.close();
    }

    @Test
    void retrieve() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();


        tx.commit();


        Employee producto1FromDb = session.find(Employee.class, 1);

        System.out.println(producto1FromDb);

    }


    @Test
    void update() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Producto p1 = new Producto(2L, "Terra", 2, 2000, LocalDate.parse("2025-01-20"), "");

        Transaction tx = session.beginTransaction();
        session.merge(p1);
        tx.commit();

        System.out.println(p1);

    }

    @Test
    void delete() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Producto p1 = new Producto(2L, "Terra", 2, 2000, LocalDate.parse("2025-01-20"), "");

        Transaction tx2 = session.beginTransaction();
        session.remove(p1);
        tx2.commit();

        System.out.println(p1);
    }
}
