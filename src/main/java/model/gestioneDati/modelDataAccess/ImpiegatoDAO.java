package model.gestioneDati.modelDataAccess;

import controller.gestioneUtenza.MyRuntimeException;
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
}
