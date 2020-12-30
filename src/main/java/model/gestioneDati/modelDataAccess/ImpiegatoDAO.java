package model.gestioneDati.modelDataAccess;

import controller.gestioneUtenza.MyRuntimeException;
import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Impiegato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImpiegatoDAO {
    /**
     *
     * @param email
     * @param pwd
     * @return Impiegato.
     */
    public Impiegato doLogin(String email, String pwd) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement("select * from impiegato "
                                    + "where email = ? and pwd = SHA1(?)");
            statement.setString(1, email);
            statement.setString(2, pwd);
            ResultSet r = statement.executeQuery();
            if (r.next()) {
                Impiegato i = new Impiegato();
                i.setMatricola(r.getString("Matricola"));
                i.setCF(r.getString("CF"));
                i.setNome(r.getString("Nome"));
                i.setCognome(r.getString("Cognome"));
                i.setPwd(r.getString("Pwd"));
                i.setVia(r.getString("Via"));
                i.setCivico(r.getInt("Civico"));
                i.setCitta(r.getString("Città"));
                i.setEmail(r.getString("Email"));
                i.setNumSegnalazioniApp(r.getInt("numSegnalazioniApp"));
                i.setNumSegnalazioniChiuse(r.getInt("numSegnalazioniApp"));
                return i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void doRegister(Impiegato impiegato) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into impiegato"
                            + " values(?,?,?,?,sha1(?),?,?,?,?,0,0)");
            statement.setString(1, impiegato.getMatricola());
            statement.setString(2, impiegato.getCF());
            statement.setString(3, impiegato.getNome());
            statement.setString(4, impiegato.getCognome());
            statement.setString(5, impiegato.getPwd());
            statement.setString(6, impiegato.getVia());
            statement.setInt(7, impiegato.getCivico());
            statement.setString(8, impiegato.getCitta());
            statement.setString(9, impiegato.getEmail());
            if (statement.executeUpdate() != 1) {
                throw new MyRuntimeException("C'è stato"
                        + "un errore nella registrazione");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doUpdate(Impiegato impiegato) {
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE impiegato SET "
                            + "Nome=?, Cognome=?, Pwd=?,"
                            + "Via=?, Civico=?,"
                            + "Città=?, numSegnalazioniApp=?,"
                            + "numSegnalazioniChiuse=? WHERE Matricola=?");
            statement.setString(1, impiegato.getNome());
            statement.setString(2, impiegato.getCognome());
            statement.setString(3, impiegato.getPwd());
            statement.setString(4, impiegato.getVia());
            statement.setInt(5, impiegato.getCivico());
            statement.setString(6, impiegato.getCitta());
            statement.setInt(7, impiegato.getNumSegnalazioniApp());
            statement.setInt(8, impiegato.getNumSegnalazioniChiuse());
            statement.setString(9, impiegato.getMatricola());
            if (statement.executeUpdate() != 1) {
                throw new MyRuntimeException(
                        "C'è stato un errore nella modifica "
                                + "dell'impiegato");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doDelete(String matricola) throws MyServletException {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "delete from impiegato where Matricola=?");
            statement.setString(1, matricola);
            if (statement.executeUpdate() != 1) {
                throw new MyServletException("C'è stato"
                        + " un errore nell'eliminazione dell'impiegato");
            }
        } catch (SQLException e) {
            throw new MyServletException("C'è stato"
                    + " un errore nell'eliminazione del profilo");
        }
    }

    public void doUpdatePasswordByEmail(String email, String password) {
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE impiegato SET pwd= SHA1(?) WHERE email=?");
            ps.setString(1, password);
            ps.setString(2, email);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Errore"
                        + "durante update password impiegato");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Impiegato doRetrieveByEmail(String email) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM impiegato WHERE email=?");
            statement.setString(1, email);
            ResultSet r = statement.executeQuery();
            if (r.next()) {
                Impiegato i = new Impiegato();
                i.setMatricola(r.getString("Matricola"));
                i.setCF(r.getString("CF"));
                i.setNome(r.getString("Nome"));
                i.setCognome(r.getString("Cognome"));
                i.setPwd(r.getString("Pwd"));
                i.setVia(r.getString("Via"));
                i.setCivico(r.getInt("Civico"));
                i.setCitta(r.getString("Città"));
                i.setEmail(r.getString("Email"));
                i.setNumSegnalazioniApp(r.getInt("numSegnalazioniApp"));
                i.setNumSegnalazioniChiuse(r.getInt("numSegnalazioniChiuse"));
                return i;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
