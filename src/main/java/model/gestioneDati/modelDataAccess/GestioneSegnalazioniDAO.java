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

/**
 * Classe che controlla le operazioni effettuate dagli impiegati
 * alle segnalazioni nel database.
 */
public class GestioneSegnalazioniDAO {
    /**
     *Il metodo si connette col database, crea un comando
     * SQL in cui si specifica e si esegue
     * l'operazione di inserimento del tipo di operazione
     * effettuata da un impiegato relativa ad una segnalazione nel database.
     * Se si rileva un errore si lancia un'eccezione
     * con il relativo messaggio informativo.
     * @param impiegato oggetto che identifica l'impegato.
     * @param segnalazione oggetto che identifica la segnalazione.
     * precondizioni: impiegato != null && segnalazione != null
     * @throws MyRuntimeException se si verifica un errore
     * nell'inserimento dell'operazione.
     */
    public void doInsert(Impiegato impiegato, Segnalazione segnalazione) {
        try (Connection connection = ConnectionPool.getConnection()) {
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
     *Il metodo si connette col database, crea un comando
     * SQL in cui si specifica e si esegue
     * l'operazione che, prende gli impiegati che hanno lavorato
     * ad una determinata segnalazione, nel database.
     * Se si rileva un errore si lancia un'eccezione
     * con il relativo messaggio informativo.
     * @param idSegnalazione identificativo della segnalazione.
     * precondizioni: idSegnalazione > 0
     * @return impiegati lista di impiegati che hanno lavorato
     * ad una determinata segnalazione.
     * postcondizione: impiegati.size >= 0
     * @throws RuntimeException se si verifica un errore nel database.
     */
    public List<Impiegato> doRetrieveImpiegatiOsservatori(int idSegnalazione) {
        try (Connection connection = ConnectionPool.getConnection()) {
            ArrayList<Impiegato> impiegati = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM gestionesegnalazioni "
                            + "JOIN impiegato i "
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
