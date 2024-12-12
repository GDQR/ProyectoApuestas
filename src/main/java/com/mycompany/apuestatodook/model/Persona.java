/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apuestatodook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Tango
 */

@Entity
@Table (name="Persona")
public class Persona {
     
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_persona;
    @Column(length=255)
    private String dni;
    @Column(length=255)
    private String nombre;
    @Column(length=255)
    private String apellido;
    private int edad;
    @Column(name="fk_id_usuario")
    private int fk_id_ususario;

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public Persona(){
        
    }
    
    public Persona(int idUsuario, String nombre, String apellido, int edad, String dni){
        this.fk_id_ususario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
    }
    
    public Persona(int id_persona, String dni, String nombre, String apellido, int edad, int fk_id_ususario) {
        this.id_persona = id_persona;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fk_id_ususario = fk_id_ususario;
    }
    
}
