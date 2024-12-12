
package com.mycompany.apuestatodook.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class PersonaDAO {

    private List<Partido> personas;

    public PersonaDAO() {
        this.personas = new ArrayList<>();
    }

    public void agregarPersona(int idUsuario, String nombre, String apellido, int edad, String dni) {
        Persona persona = new Persona(idUsuario, nombre, apellido, edad, dni);
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession() ){
            session.beginTransaction();
            session.persist(persona);
            session.getTransaction().commit();
            
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    
    public Persona getPersonaPorId(Integer Id) {
        Persona persona = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession() ){
            session.beginTransaction();
            persona = session.createQuery("FROM Persona WHERE fk_id_ususario = ?1", Persona.class)
                    .setParameter(1, Id)
                    .getSingleResultOrNull();
            session.getTransaction().commit();
            
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return persona;
    }

    private Persona rsRowToPersona(ResultSet rs) throws SQLException {
       int id_persona = rs.getInt(1);
       String dni = rs.getString(2);
       String nombre = rs.getString(3);
       String apellido = rs.getString(4);
       int edad = rs.getInt(5);
       int fk_usuario = rs.getInt(5);
       
       return new Persona(id_persona, dni, nombre, apellido, edad, fk_usuario) ;
    }

}
    