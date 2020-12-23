package model.gestioneDati.modelDataAccess;

import controller.gestioneUtenza.MyRuntimeException;
import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Impiegato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class GestioneSegnalazioniDAO {
    public void doInsert(Impiegato impiegato, String stato, int id) {
        try {
            Connection connection = ConnectionPool.getConnection();
            FacadeDAO service = new FacadeDAO();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO gestionesegnalazioni" +
                    "(Impiegato, Segnalazione, TipoLavorazione, DataLavorazione)" +
                    "VALUES(?,?,?,?)");
            statement.setString(1,impiegato.getMatricola());
            statement.setInt(2,service.getSegnalazioneById(id).getId());
            statement.setString(3,stato);
            statement.setDate(4,new java.sql.Date(new Date().getTime()));
            if(statement.executeUpdate()!=1)
                throw new MyRuntimeException("C'è stato un errore nell'inserimento della lavorazione");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
