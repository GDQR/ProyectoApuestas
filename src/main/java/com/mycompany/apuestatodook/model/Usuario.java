package com.mycompany.apuestatodook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="Usuario")
public class Usuario{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private int IDusuario;
    @Column(length=255)
    private String usuario;
    @Column(length=255)
    private String contrasenia;
    private double dinero;
    @Column(name="rol")
    private String tipo;
    
    public Usuario(){
        
    }
  
    public Usuario(int IDusuario, String usuario, String contrasenia, double dinero, String tipo) {
        this.IDusuario = IDusuario;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.dinero = dinero;
        this.tipo = tipo;
    }
    
    

    public String getTipo() {
        return tipo;
    }
    
    public Usuario(int IDusuario, String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.IDusuario= IDusuario;
    }

    public double getDinero() {
        return dinero;
    }

    public int getIDusuario() {
        return IDusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }


    public String getNombre() {
        return usuario;
    }

    public String getContrasena() {
        return contrasenia;
    }

    public void setIDusuario(int IDusuario) {
        this.IDusuario = IDusuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "IDusuario=" + IDusuario + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", dinero=" + dinero + '}';
    }

}


