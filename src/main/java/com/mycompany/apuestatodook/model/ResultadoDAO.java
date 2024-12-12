package com.mycompany.apuestatodook.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;

public class ResultadoDAO {
    private List<Resultado> resultados;

    public ResultadoDAO() {
        this.resultados = new ArrayList<>();
    }

    public void add(Resultado resultado) {
        resultados.add(resultado);
    }

    public List<Resultado> getAllResultados() {
        List<Resultado> resultados = new LinkedList<>();
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession() ){
            session.beginTransaction();
            resultados = session.createQuery("FROM Resultado", Resultado.class)
                    .getResultList();
            session.getTransaction().commit();
            
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return resultados;
    }

    public Resultado getResultadoByIdPartido(int idPartido) {
        Resultado resultado = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession() ){
            session.beginTransaction();
            resultado = session.createQuery("FROM Resultado WHERE idPartido = ?1", Resultado.class)
                    .setParameter(1, idPartido)
                    .getSingleResultOrNull();
            session.getTransaction().commit();
            
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return resultado;
    }
    
     public int getIdResultadoByIdPartido(int idPartido) {
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession() ){
            session.beginTransaction();
            int id = session.createQuery("SELECT idResultado FROM Resultado WHERE idPartido = ?1", int.class)
                    .setParameter(1, idPartido)
                    .getSingleResult();
            
            session.getTransaction().commit();
            
            return id;
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    private Resultado rsRowToResultado(ResultSet rs) throws SQLException {
        int idResultado = rs.getInt("id_resultado");
        String ganador = rs.getString("ganador");
        int idPartido = rs.getInt("fk_id_partido");
        return new Resultado(idResultado, ganador, idPartido);
    }
}