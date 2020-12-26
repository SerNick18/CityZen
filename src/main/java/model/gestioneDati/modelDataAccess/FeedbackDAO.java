package model.gestioneDati.modelDataAccess;

import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.gestioneUtenza.MyRuntimeException;
import model.gestioneDati.modelObjects.Feedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedbackDAO {

    public void doInsertFeedback(Feedback feedback){
        try {
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "insert into feedback (Cittadino, Segnalazione, Descrizione,"
                            + "valutazione, DataFeedback)"
                            + "values(?,?,?,?,?)");
            statement.setString(1, feedback.getCittadino().getCF());
            statement.setInt(2, feedback.getSegnalazione().getId());
            statement.setString(3, feedback.getDescrizione());
            statement.setInt(4, feedback.getValutazione());
            statement.setDate(5,
                    new java.sql.Date(feedback.getDataFeedback()
                            .getTime()));
            if (statement.executeUpdate() != 1) {
                throw new MyRuntimeException(
                        "C'è stato un errore nell'inserimento del "
                                + "feedback");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Feedback> doRetrieveFeedBackBySegnalazione(int id) {
        try {
            ArrayList<Feedback> feedbacks = new ArrayList<>();
            CittadinoDAO cittadinoDAO = new CittadinoDAO();
            SegnalazioneDAO segnalazioneDAO=new SegnalazioneDAO();
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM feedback WHERE feedback.Segnalazione=?;");
            statement.setInt(1, id);
            ResultSet r = statement.executeQuery();
            while (r.next()) {
                Feedback f=new Feedback();
                f.setSegnalazione(segnalazioneDAO.doRetrieveById(r.getInt("Segnalazione")));
                f.setCittadino(cittadinoDAO.doRetrieveByCF(r.getString("Cittadino")));
                f.setDescrizione(r.getString("Descrizione"));
                f.setDataFeedback(r.getDate("DataFeedback"));
                f.setValutazione(r.getInt("Valutazione"));
                feedbacks.add(f);
            }
            return feedbacks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isCittadinoFeedbackSegnalazione (String cFCittadino, int idSegnalazione){
        try{
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM feedback AS f WHERE f.cittadino=? AND f.segnalazione=?");
            statement.setString(1, cFCittadino);
            statement.setInt(2,idSegnalazione);
            ResultSet r = statement.executeQuery();
            return r.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
