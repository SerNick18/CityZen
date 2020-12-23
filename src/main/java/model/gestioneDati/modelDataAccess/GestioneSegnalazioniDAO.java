package model.gestioneDati.modelDataAccess;

import controller.gestioneUtenza.MyRuntimeException;
import model.gestioneDati.modelObjects.Impiegato;
import model.gestioneDati.modelObjects.Segnalazione;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestioneSegnalazioniDAO {
    /**
     *
     * @param impiegato
     * @param segnalazione
     */
    public void doInsert(Impiegato impiegato, Segnalazione segnalazione) {
        try {
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO gestionesegnalazioni"
                    + "(Impiegato, Segnalazione,"
                            + "TipoLavorazione, DataLavorazione)"
                    + "VALUES(?,?,?,?)");
            statement.setString(1, impiegato.getMatricola());
            statement.setInt(2, segnalazione.getId());
            statement.setString(3, segnalazione.getStato());
            statement.setDate(4, new java.sql.Date(new Date().getTime()));
            if (statement.executeUpdate() != 1) {
                throw new MyRuntimeException("C'è stato un errore"
                        + "nell'inserimento della lavorazione");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param idSegnalazione
     * @return lista di Impiegati
     */
    public List<Impiegato> doRetrieveImpiegatiOsservatori(int idSegnalazione) {
        try {
            ArrayList<Impiegato> impiegati = new ArrayList<>();
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM gestionesegnalazioni"
                            + "JOIN Impiegato i"
                            + "ON gestionesegnalazioni.Impiegato=i.Matricola "
                            + "WHERE segnalazione=? ");
            statement.setInt(1, idSegnalazione);
            ResultSet r = statement.executeQuery();
            while (r.next()) {
                Impiegato i = new Impiegato(
                        r.getString("Email"),
                        r.getString("Matricola"),
                        r.getString("Pwd"),
                        r.getString("CF"),
                        r.getString("Nome"),
                        r.getString("Cognome"),
                        r.getString("Via"),
                        r.getInt("Civico"),
                        r.getString("Città"),
                        r.getInt("numSegnalazioniApp"),
                        r.getInt("NumSegnalazioniChiuse"));
                impiegati.add(i);
            }
            return impiegati;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
