package com.mycompany.apuestatodook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="Resultado")
public class Resultado {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_resultado")
    private int idResultado;
    @Column(length=255)
    private String ganador;
    @Column(name="fk_id_partido")
    private int idPartido;

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }
    
    public Resultado(){
        
    }

    public Resultado(int idResultado, String ganador, int idPartido) {
        this.idResultado = idResultado;
        this.ganador = ganador;
        this.idPartido = idPartido;
    }
    
    public Resultado(String ganador) {
        this.ganador = ganador;
    }

    public int getIdResultado() {
        return idResultado;
    }

    public String getGanador() {
        return ganador;
    }

    public int getIdPartido() {
        return idPartido;
    }
}