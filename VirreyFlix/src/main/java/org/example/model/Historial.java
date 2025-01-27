package org.example.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Historial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date fecha_reproduccion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private Perfil perfil;


}
