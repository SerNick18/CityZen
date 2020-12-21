package model.gestioneDati.modelDataAccess;

import controller.gestioneUtenza.MyRuntimeException;
import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.modelObjects.Cittadino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Questa classe effettua il recupero, il salvataggio e le modifiche,
 * di un Cittadino, nel database.
 * */
public class CittadinoDAO {
    /**
     * Metodo che effettua, dati email e password,
     * l'accesso al sistema.
     * */
    public Cittadino doLogin(String email, String pwd){
        try {
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from cittadino where email = ? and pwd = SHA1(?)");
            statement.setString(1,email);
            statement.setString(2,pwd);
            ResultSet r = statement.executeQuery();
            if (r.next()){
                Cittadino c = new Cittadino(r.getString(1),r.getString(2),r.getString(3),
                        r.getString(4), r.getString(5),r.getInt(6), r.getString(7),
                        r.getString(8), r.getInt(9), r.getInt(10));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Metodo che effettua la registrazione del Cittadino
     * nel sistema.
     * */
    public void doRegister(Cittadino cittadino){
        try {
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into cittadino" +
                    " values(?,?,?,sha1(?),?,?,?,?,0,0)");
            statement.setString(1,cittadino.getCF());
            statement.setString(2,cittadino.getNome());
            statement.setString(3,cittadino.getCognome());
            statement.setString(4,cittadino.getPwd());
            statement.setString(5,cittadino.getVia());
            statement.setInt(6,cittadino.getCivico());
            statement.setString(7,cittadino.getCitta());
            statement.setString(8,cittadino.getEmail());
            if(statement.executeUpdate()!=1)
                throw new MyRuntimeException("C'è stato un errore nella registrazione");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metodo che verifica la presenza di un Cittadino
     * con una determinata email.
     * nel database
     * */
    public Cittadino doRetrieveByEmail(String email) {
        try {
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM cittadino WHERE email=?");
            statement.setString(1, email);
            ResultSet r = statement.executeQuery();
            if (r.next()) {
                Cittadino c = new Cittadino(r.getString(1),r.getString(2),r.getString(3),
                        r.getString(4), r.getString(5),r.getInt(6), r.getString(7),
                        r.getString(8), r.getInt(9), r.getInt(10));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Metodo che verifica la presenza di un Cittadino
     * con un determinato codice fiscale.
     * */
    public Cittadino doRetrieveByCF(String cf) {
        try {
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM cittadino WHERE cf=?");
            statement.setString(1, cf);
            ResultSet r = statement.executeQuery();
            if (r.next()) {
                Cittadino c = new Cittadino(r.getString(1),r.getString(2),r.getString(3),
                        r.getString(4), r.getString(5),r.getInt(6), r.getString(7),
                        r.getString(8), r.getInt(9), r.getInt(10));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
