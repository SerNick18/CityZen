package model.gestioneDati.modelDataAccess;

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
}
