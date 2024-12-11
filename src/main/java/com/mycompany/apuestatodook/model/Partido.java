package com.mycompany.apuestatodook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@Entity
@Table (name="Partido")
public class Partido {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_partido")
    private int idPartido;
    @Column(length=255)
    private String local;
    @Column(length=255)
    private String visitante;
    @Column(length=255)
    private String fecha;
    @Transient
    private Resultado resultado;

    public Resultado getResultado() {
        return resultado;
    }
    
    public void setResultado(Resultado resultado) {
    this.resultado = resultado;
    }
    
    public Partido(){
        
    }

    public Partido(String local, String visitante, String fecha, int idPartido) {
        this.local = local;
        this.visitante = visitante;
        this.fecha = fecha;
        this.idPartido = idPartido;
    }
     public Partido(String local, String visitante, String fecha, Resultado resultado) {
        this.local = local;
        this.visitante = visitante;
        this.fecha = fecha;
        this.resultado = resultado;
    }
    

    public void setLocal(String local) {
        this.local = local;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public String getLocal() {
        return local;
    }

    public String getVisitante() {
        return visitante;
    }

    public String getFecha() {
        return fecha;
    }

    public int getIdPartido() {
        return idPartido;
    }
    
    
   

    
    
    
    
}
