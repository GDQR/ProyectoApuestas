package com.mycompany.apuestatodook.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class ApuestaDAO {

    public void add(Apuesta apuesta) {
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession() ){
            session.beginTransaction();
            session.persist(apuesta);
            session.getTransaction().commit();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public List<Apuesta> getApuestasPorUsuario(int idUsuario) {
        List<Apuesta> apuestas = new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession() ){
            session.beginTransaction();
            apuestas = session.createQuery("FROM Apuesta WHERE idUsuario = ?1", Apuesta.class)
                    .setParameter(1, idUsuario)
                    .getResultList();
            session.getTransaction().commit();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return apuestas;
    }
    
    public String getResultadoPorPartido(int idPartido) {
        String resultado = null;
        
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession() ){
            session.beginTransaction();
            resultado = session.createQuery("SELECT ganador FROM Resultado WHERE idPartido = ?1", String.class)
                    .setParameter(1, idPartido)
                    .getSingleResultOrNull();
            
            session.getTransaction().commit();
            
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
        
        return resultado;
    }


    private Apuesta rsRowToApuesta(ResultSet rs) throws SQLException {
        int monto = rs.getInt("monto");
        String por_quien = rs.getString("por_quien");
        int idUsuario = rs.getInt("fk_id_usuario");
        int idPartido = rs.getInt("fk_id_partido");
        int fk_id_resultado = rs.getInt("fk_id_resultado");

        Apuesta apuesta = new Apuesta(monto, por_quien, idUsuario, idPartido, fk_id_resultado);

        return apuesta;
    }
    
    public void updateEstado(Apuesta apuesta) {
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession() ){
            System.out.println("ID de apuesta antes de la actualización: " + apuesta.getIdApuesta());
            session.beginTransaction();
            session.createMutationQuery("UPDATE Apuesta SET estado = ?1 WHERE idApuesta = ?2")
                    .setParameter(1, apuesta.getEstado())
                    .setParameter(2, apuesta.getIdApuesta())
                    .executeUpdate();
            
            session.getTransaction().commit();
            System.out.println("Estado actualizado: " + apuesta.getEstado());
        } catch(Exception ex){
            System.out.println("Error al actualizar el estado: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    public List<Apuesta> getAllApuestasConResultado() {
        List<Apuesta> apuestasConResultado = new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession() ){
            session.beginTransaction();
            List<Object[]> resultados = 
                    session.createQuery("SELECT a.por_quien, a.monto, p.local, p.visitante, p.fecha "+
                            "FROM Apuesta as a "+
                            "JOIN Resultado as r ON r.idPartido = a.idPartido "+
                            "JOIN Partido as p ON p.idPartido = a.idPartido",Object[].class)
                            .getResultList();
            
            for (var resultado : resultados) {
                String por_quien = (String) resultado[0];
                int monto = (int) resultado[1];
                String local = (String) resultado[2];
                String visitante = (String) resultado[3];
                String fecha = (String) resultado[4];
                Apuesta apuesta = new Apuesta(local, visitante, fecha, monto, por_quien);
                apuestasConResultado.add(apuesta);
            }
            
            session.getTransaction().commit();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return apuestasConResultado;
    }
    public List<Apuesta> getApuestasConResultadoPorUsuario(int idUsuario) {
        List<Apuesta> apuestasConResultado = new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession() ){
            session.beginTransaction();
            List<Object[]> resultados = 
                    session.createQuery("SELECT a.por_quien, a.monto, p.local, p.visitante, p.fecha "+
                            "FROM Apuesta as a "+
                            "JOIN Resultado as r ON r.idPartido = a.idPartido "+
                            "JOIN Partido as p ON p.idPartido = a.idPartido "+
                            "WHERE a.idUsuario = ?1",Object[].class)
                            .setParameter(1, idUsuario)
                            .getResultList();
            
            for (var resultado : resultados) {
                String por_quien = (String) resultado[0];
                int monto = (int) resultado[1];
                String local = (String) resultado[2];
                String visitante = (String) resultado[3];
                String fecha = (String) resultado[4];
                Apuesta apuesta = new Apuesta(local, visitante, fecha, monto, por_quien);
                apuestasConResultado.add(apuesta);
            }
            
            session.getTransaction().commit();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return apuestasConResultado;
    }

}