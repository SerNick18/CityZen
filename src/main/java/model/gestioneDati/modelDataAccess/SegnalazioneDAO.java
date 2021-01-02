package model.gestioneDati.modelDataAccess;

import controller.gestioneUtenza.MyRuntimeException;
import controller.gestioneUtenza.MyServletException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Segnalazione;
import model.gestioneDati.modelObjects.SegnalazioneInterface;
import model.gestioneDati.modelObjects.SegnalazioneProxy;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SegnalazioneDAO {
    /**.
     * Metodo che ritorna la lista di SegnalazioneProxy inoltrate
     * @param offset numero di riga dal quale iniziare
     *  a prendere le segnalazioni - precondizione: offset > 0
     * @return listaSegnalazioni lista di SegnalazioneProxy inoltrate
     * forEach(s:listaSegnalazioni) s.stato="inoltrata"
     * || listaSegnalazioni==null
     */
    public List<SegnalazioneInterface> doRetrieveInoltrateProxy(int offset) {
        try (Connection connection = ConnectionPool.getConnection()) {
            ArrayList<SegnalazioneInterface> segnalazioni = new ArrayList<>();
            FacadeDAO facadeDAO = new FacadeDAO();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM segnalazione WHERE stato=? "
                            + "order by id desc limit 6 offset ?");
            statement.setString(1, "inoltrata");
            statement.setInt(2, offset);
            ResultSet r = statement.executeQuery();
            while (r.next()) {
                SegnalazioneProxy s = new SegnalazioneProxy(
                        r.getInt("ID"), r.getString("Oggetto"),
                        r.getInt("Priorità"),
                        facadeDAO.getCittadinoByCf(r.getString("Cittadino")),
                        r.getInt("numSolleciti"));
                segnalazioni.add(s);
            }
            return segnalazioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**.
     * Metodo che ritorna la lista delle segnalazioni per stato
     * @param stato stato della segnalazione - precondizione:
     *              stato=="inoltrata" || stato=="approvata"
     *              || stato=="chiusa" || stato=="rifiutata"
     * @param offset numero di riga dal quale iniziare
     *  a prendere le segnalazioni - precondizione: offset > 0
     * @return listaSegnalazioni lista di segnalazioni - postcondizione:
     * forEach(s:listaSegnalazioni) s.stato=stato
     * || listaSegnalazioni==null
     */
    public List<SegnalazioneInterface> doRetrieveByStato(
            String stato, int offset) {
        try (Connection connection = ConnectionPool.getConnection()) {
            ArrayList<SegnalazioneInterface> segnalazioni = new ArrayList<>();
            FacadeDAO facadeDAO = new FacadeDAO();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM segnalazione WHERE stato=? "
                            + "order by id desc limit 20 offset ?");
            statement.setString(1, stato);
            statement.setInt(2, offset);
            ResultSet r = statement.executeQuery();
            while (r.next()) {
                SegnalazioneProxy s = new SegnalazioneProxy(
                        r.getInt("ID"), r.getString("Oggetto"),
                        r.getInt("Priorità"),
                        facadeDAO.getCittadinoByCf(r.getString("Cittadino")),
                        r.getInt("numSolleciti"));
                segnalazioni.add(s);
            }
            return segnalazioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**.
     * Metodo per ricercare una segnalazione dato un id
     * @param id identificativo della segnalazione - precondizione:
     *           id > 0
     * @return s segnalazione con l'id passato come parametro
     * - postcondizione: s.id = id
     */
    public Segnalazione doRetrieveById(int id) {
        try (Connection connection = ConnectionPool.getConnection()) {
            FacadeDAO facadeDAO = new FacadeDAO();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM segnalazione WHERE id=?");
            statement.setInt(1, id);
            ResultSet r = statement.executeQuery();
            if (r.next()) {
                Segnalazione s = new Segnalazione(
                        r.getInt("ID"), r.getString("Via"),
                        r.getInt("Civico"), r.getInt("Priorità"),
                        r.getInt("numSolleciti"),
                        r.getString("Stato"),
                        r.getDate("DataSegnalazione"),
                        r.getString("Oggetto"),
                        r.getString("Descrizione"),
                        r.getString("Foto"),
                        facadeDAO.getCittadinoByCf(r.getString("Cittadino")),
                        r.getInt("Riaperta"));
                return s;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  Metodo che restituisce una lista di segnalazioni
     *  appartenenti ad uno specifico cittadino.
     * @param cf del cittadino - precondizione: cf != null
     * @param offset numero di riga dal quale iniziare
     *  a prendere le segnalazioni - precondizione offset > 0
     * @return listaSegnalazioni lista di segnalazioni appartenenti
     * al cittadino specificato - postcondizione:
     * forEach(s:listaSegnalazioni) s.cittadino.cf==cf
     * || listaSegnalazioni == null
     */
    public List<SegnalazioneInterface> doRetrieveByCittadino(String cf,
                                                             int offset) {
        ArrayList<SegnalazioneInterface> segnalazioni = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            FacadeDAO facadeDAO = new FacadeDAO();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM segnalazione WHERE Cittadino=?"
            + " order by id desc limit 20 offset ?");
            statement.setString(1, cf);
            statement.setInt(2, offset);
            ResultSet r = statement.executeQuery();
            while (r.next()) {
                Segnalazione s = new Segnalazione(r.getInt("ID"),
                        r.getString("Via"),
                        r.getInt("Civico"), r.getInt("Priorità"),
                        r.getInt("numSolleciti"),
                        r.getString("Stato"), r.getDate("DataSegnalazione"),
                        r.getString("Oggetto"),
                        r.getString("Descrizione"), r.getString("Foto"),
                        facadeDAO.getCittadinoByCf(r.getString("Cittadino")),
                        r.getInt("Riaperta"));
                segnalazioni.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return segnalazioni;
    }

    /**
     * Il metodo ha il compito di memorizzare nel database una segnalazione.
     * Riceve la segnalazione sotto forma di oggetto, estrapola tutti i suoi
     * campi, e li inserisce in una query da eseguire sul database.
     * @param segnalazione da inserire nel database - precondizione:
     * segnalazione!=null - postcondizione:
     * SegnalazioneDAO.doRetrieveById(segnalazione.id)!=null
     */
    public void doInsert(Segnalazione segnalazione) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into segnalazione (Via, Civico, Priorità,"
                            + "numSolleciti, Stato, DataSegnalazione, Oggetto,"
                            + "Descrizione, Foto, Cittadino, Riaperta)"
                            + "values(?,?,?,?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, segnalazione.getVia());
            statement.setInt(2, segnalazione.getCivico());
            statement.setInt(3, segnalazione.getPriorita());
            statement.setInt(4, segnalazione.getNumSolleciti());
            statement.setString(5, segnalazione.getStato());
            statement.setDate(6,
                    new java.sql.Date(segnalazione.getDataSegnalazione()
                            .getTime()));
            statement.setString(7, segnalazione.getOggetto());
            statement.setString(8, segnalazione.getDescrizione());
            statement.setString(9, segnalazione.getFoto());
            statement.setString(10, segnalazione.getCittadino().getCF());
            statement.setInt(11, segnalazione.getRiaperta());
            if (statement.executeUpdate() != 1) {
                throw new MyRuntimeException(
                        "C'è stato un errore nell'inserimento della "
                                + "segnalazione");
            }
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            segnalazione.setId(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**.
     * Metodo per modificare una segnalazione.
     * @param segnalazione segnalazione modificata che sostituirà
     *                     quella già presente nel database.
     *                     - precondizione:
     *                     segnalazione!=null
     *                     - postcondizione:
     * SegnalazioneDAO.doRetrieveById(segnalazione.id)==segnalazione
     */
    public void doUpdate(Segnalazione segnalazione) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE segnalazione SET "
                            + "Via=?, Civico=?, Priorità=?, numSolleciti=?,"
                            + "Stato=?, DataSegnalazione=?,"
                            + "Oggetto=?, Descrizione=?, Foto=?,"
                            + "Cittadino=?, Riaperta=? WHERE ID=?");
            statement.setString(1, segnalazione.getVia());
            statement.setInt(2, segnalazione.getCivico());
            statement.setInt(3, segnalazione.getPriorita());
            statement.setInt(4, segnalazione.getNumSolleciti());
            statement.setString(5, segnalazione.getStato());
            statement.setDate(6,
                    new java.sql.Date(segnalazione.getDataSegnalazione()
                            .getTime()));
            statement.setString(7, segnalazione.getOggetto());
            statement.setString(8, segnalazione.getDescrizione());
            statement.setString(9, segnalazione.getFoto());
            statement.setString(10, segnalazione.getCittadino().getCF());
            statement.setInt(11, segnalazione.getRiaperta());
            statement.setInt(12, segnalazione.getId());
            if (statement.executeUpdate() != 1) {
                throw new MyRuntimeException(
                        "C'è stato un errore nella modifica "
                                + "della segnalazione");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo per eliminare una segnalazione.
     * @param ID della segnalazione da eliminare - precondizione:
     *           id>0
     *           - postcondizione:
     *           SegnalazioneDAO.doRetrieveById(ID)==null
     * @throws MyServletException in caso di errore in fase
     * di eliminazione
     */
    public void doDelete(int ID) throws MyServletException {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM segnalazione WHERE ID=?");
            statement.setInt(1, ID);
            if (statement.executeUpdate() != 1) {
                throw new MyServletException("C'è stato un errore "
                        + "nell'eliminazione della segnalazione");
            }
        } catch (SQLException e) {
            throw new MyServletException("C'è stato un errore "
                    + "nell'eliminazione della segnalazione");
        }
    }
}
