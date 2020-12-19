package model.gestioneDati.modelDataAccess;

import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Segnalazione;
import model.gestioneDati.modelObjects.SegnalazioneInterface;
import model.gestioneDati.modelObjects.SegnalazioneProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
