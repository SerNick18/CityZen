package model.gestioneDati.modelDataAccess;

import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Feedback;
import model.gestioneDati.modelObjects.Segnalazione;
import model.gestioneDati.modelObjects.SegnalazioneInterface;
import model.gestioneDati.modelObjects.SegnalazioneProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {
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
}
