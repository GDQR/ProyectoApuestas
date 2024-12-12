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
//        List<Resultado> resultados = new LinkedList<>();
//        String query = "SELECT * FROM resultado";
//        try (Connection con = ConnectionPool.getInstance().getConnection();
//             PreparedStatement preparedStatement = con.prepareStatement(query);
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//            while (resultSet.next()) {
//                resultados.add(rsRowToResultado(resultSet));
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        return resultados;
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
//        String query = "SELECT * FROM resultado WHERE fk_id_partido = ?";
//        Resultado resultado = null;
//        try (Connection con = ConnectionPool.getInstance().getConnection();
//             PreparedStatement preparedStatement = con.prepareStatement(query)) {
//            preparedStatement.setInt(1, idPartido);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    resultado = rsRowToResultado(resultSet);
//                }
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        
//        return resultado;
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
//        String query = "SELECT id_resultado FROM resultado WHERE fk_id_partido = ?";
//        try (Connection con = ConnectionPool.getInstance().getConnection();
//             PreparedStatement preparedStatement = con.prepareStatement(query)) {
//            preparedStatement.setInt(1, idPartido);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    return resultSet.getInt("id_resultado");
//                } else {
//                    throw new RuntimeException("No se encontró ningún resultado para el partido con id " + idPartido);
//                }
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
    }

    private Resultado rsRowToResultado(ResultSet rs) throws SQLException {
        int idResultado = rs.getInt("id_resultado");
        String ganador = rs.getString("ganador");
        int idPartido = rs.getInt("fk_id_partido");
        return new Resultado(idResultado, ganador, idPartido);
    }
}