package model.gestioneDati.modelDataAccess;

import controller.gestioneUtenza.MyRuntimeException;
import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.modelObjects.Cittadino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**.
 * Questa classe effettua il recupero, il salvataggio e le modifiche,
 * di un Cittadino, nel database.
 * */
public class CittadinoDAO {
    /**.
     * Il metodo si connette col database, crea un comando
     * SQL in cui si specifica e si esegue
     * l'operazione di eliminazione del cittadino dal database.
     * Se si rileva un errore si lancia un'eccezione
     * con il relativo messaggio informativo.
     * @param cf del cittadino da eliminare
     * @throws MyServletException se si verifica un errore
     * nell'eliminazione del cittadino
     */
    public void doDelete(String cf) throws MyServletException {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "delete from cittadino where CF=?");
            statement.setString(1, cf);
            if (statement.executeUpdate() != 1) {
                throw new MyServletException("C'è stato"
                        + "un errore nell'eliminazione del profilo");
            }
        } catch (SQLException e) {
            throw new MyServletException("C'è stato"
                   + "un errore nell'eliminazione del profilo");
        }
    }

    /**
     *
     * @param cittadino
     */
    public void doUpdate(Cittadino cittadino) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE cittadino SET "
                            + "Nome=?, Cognome=?, Pwd=?,"
                            + "Via=?, Civico=?,"
                            + "Città=?, Email=?, numSegnalazioni=?,"
                            + "numSegnAppr=? WHERE CF=?");
            statement.setString(1, cittadino.getNome());
            statement.setString(2, cittadino.getCognome());
            statement.setString(3, cittadino.getPwd());
            statement.setString(4, cittadino.getVia());
            statement.setInt(5, cittadino.getCivico());
            statement.setString(6, cittadino.getCitta());
            statement.setString(7, cittadino.getEmail());
            statement.setInt(8, cittadino.getNumSegnalazioni());
            statement.setInt(9, cittadino.getNumSegnApp());
            statement.setString(10, cittadino.getCF());
            if (statement.executeUpdate() != 1) {
                throw new MyRuntimeException(
                        "C'è stato un errore nella modifica "
                                + "del cittadino");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @param email
     * @param pwd
     * @return cittadino
     */
    public Cittadino doLogin(String email, String pwd) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from cittadino "
                            + "where email = ? and pwd = SHA1(?)");
            statement.setString(1, email);
            statement.setString(2, pwd);
            ResultSet r = statement.executeQuery();
            if (r.next()) {
                Cittadino c = new Cittadino(r.getString(1),
                        r.getString(2), r.getString(3),
                        r.getString(4), r.getString(5),
                        r.getInt(6), r.getString(7),
                        r.getString(8), r.getInt(9), r.getInt(10));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param cittadino
     */
    public void doRegister(Cittadino cittadino) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into cittadino"
                    + " values(?,?,?,sha1(?),?,?,?,?,0,0)");
            statement.setString(1, cittadino.getCF());
            statement.setString(2, cittadino.getNome());
            statement.setString(3, cittadino.getCognome());
            statement.setString(4, cittadino.getPwd());
            statement.setString(5, cittadino.getVia());
            statement.setInt(6, cittadino.getCivico());
            statement.setString(7, cittadino.getCitta());
            statement.setString(8, cittadino.getEmail());
            if (statement.executeUpdate() != 1) {
                throw new MyRuntimeException("C'è stato"
                        + "un errore nella registrazione");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param email
     * @return cittadino
     */
    public Cittadino doRetrieveByEmail(String email) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM cittadino WHERE email=?");
            statement.setString(1, email);
            ResultSet r = statement.executeQuery();
            if (r.next()) {
                Cittadino c = new Cittadino(r.getString(1),
                        r.getString(2), r.getString(3),
                        r.getString(4), r.getString(5),
                        r.getInt(6), r.getString(7),
                        r.getString(8), r.getInt(9), r.getInt(10));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param cf
     * @return cittadino
     */
    public Cittadino doRetrieveByCF(String cf) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM cittadino WHERE cf=?");
            statement.setString(1, cf);
            ResultSet r = statement.executeQuery();
            if (r.next()) {
                Cittadino c = new Cittadino(r.getString(1),
                        r.getString(2), r.getString(3),
                        r.getString(4), r.getString(5),
                        r.getInt(6), r.getString(7),
                        r.getString(8), r.getInt(9), r.getInt(10));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param email
     * @param password
     */
    public void doUpdatePasswordByEmail(String email, String password) {
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE cittadino SET pwd= SHA1(?) WHERE email=?");
            ps.setString(1, password);
            ps.setString(2, email);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Errore"
                        + "durante update password cittadino");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Questo metodo recupera dal database
     * una lista di cittadini ordinati per
     * numero di segnalazioni approvate (in
     * maniera decrescente)
     * @return la lista ordinata di cittadini
     */
    public List<Cittadino> doRetrieveByMaxApprovate(){
        List<Cittadino> cittadini = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM cittadino ORDER BY numSegnAppr DESC limit 5");
            ResultSet r = statement.executeQuery();
            while (r.next()) {
                Cittadino c = new Cittadino(r.getString(1),
                        r.getString(2), r.getString(3),
                        r.getString(4), r.getString(5),
                        r.getInt(6), r.getString(7),
                        r.getString(8), r.getInt(9), r.getInt(10));
                cittadini.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cittadini;
    }
}
