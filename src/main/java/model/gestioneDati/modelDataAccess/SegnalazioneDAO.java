package model.gestioneDati.modelDataAccess;

import controller.gestioneUtenza.MyRuntimeException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Segnalazione;
import model.gestioneDati.modelObjects.SegnalazioneInterface;
import model.gestioneDati.modelObjects.SegnalazioneProxy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SegnalazioneDAO {
    public List<SegnalazioneInterface> doRetrieveInoltrateProxy(int offset) {
        try {
            ArrayList<SegnalazioneInterface> segnalazioni = new ArrayList<>();
            FacadeDAO facadeDAO = new FacadeDAO();
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM segnalazione WHERE stato=? order by id desc limit 6 offset ?");
            statement.setString(1, "inoltrata");
            statement.setInt(2, offset);
            ResultSet r = statement.executeQuery();
            while (r.next()) {
                SegnalazioneProxy s = new SegnalazioneProxy(r.getInt("ID"),r.getString("Oggetto"),
                        r.getInt("Priorità"), facadeDAO.getCittadinoByCf(r.getString("Cittadino")), r.getInt("numSolleciti"));
                segnalazioni.add(s);
            }
            return segnalazioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SegnalazioneInterface> doRetrieveByStato(String stato, int offset) {
        try {
            ArrayList<SegnalazioneInterface> segnalazioni = new ArrayList<>();
            FacadeDAO facadeDAO = new FacadeDAO();
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM segnalazione WHERE stato=? order by id desc limit 20 offset ?");
            statement.setString(1, stato);
            statement.setInt(2, offset);
            ResultSet r = statement.executeQuery();
            while (r.next()) {
                SegnalazioneProxy s = new SegnalazioneProxy(r.getInt("ID"),r.getString("Oggetto"),
                        r.getInt("Priorità"), facadeDAO.getCittadinoByCf(r.getString("Cittadino")), r.getInt("numSolleciti"));
                segnalazioni.add(s);
            }
            return segnalazioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Segnalazione doRetrieveById(int id) {
        try {
            FacadeDAO facadeDAO = new FacadeDAO();
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM segnalazione WHERE id=?");
            statement.setInt(1, id);
            ResultSet r = statement.executeQuery();
            if (r.next()) {
                Segnalazione s = new Segnalazione(r.getInt("ID"),r.getString("Via"),
                        r.getInt("Civico"), r.getInt("Priorità"), r.getInt("numSolleciti"),
                        r.getString("Stato"), r.getDate("DataSegnalazione"), r.getString("Oggetto"),
                        r.getString("Descrizione"), r.getString("Foto"),
                        facadeDAO.getCittadinoByCf(r.getString("Cittadino")),
                        r.getInt("Riaperta"));
                return s;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doInsert(Segnalazione segnalazione){
        try {
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into segnalazione" +
                    "(Via, Civico, Priorità, numSolleciti, Stato, DataSegnalazione, Oggetto, Descrizione, Foto, Cittadino) "
                    +"values(?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1,segnalazione.getVia());
            statement.setInt(2,segnalazione.getCivico());
            statement.setInt(3,segnalazione.getPriorita());
            statement.setInt(4,segnalazione.getNumSolleciti());
            statement.setString(5,segnalazione.getStato());
            statement.setDate(6, new java.sql.Date(segnalazione.getDataSegnalazione().getTime()));
            statement.setString(7,segnalazione.getOggetto());
            statement.setString(8,segnalazione.getDescrizione());
            statement.setString(9,segnalazione.getFoto());
            statement.setString(10,segnalazione.getCittadino().getCF());
            if(statement.executeUpdate()!=1)
                throw new MyRuntimeException("C'è stato un errore nell'inserimento della segnalazione");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doSetStateById(String stato, int id){
        try {
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE segnalazione" +
                    "SET Stato=? WHERE ID=?");
            statement.setString(1, stato);
            statement.setInt(2, id);
            if(statement.executeUpdate()!=1)
                throw new MyRuntimeException("C'è stato un errore nella modifica dello stato della segnalazione");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
