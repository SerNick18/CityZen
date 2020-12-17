package Model.GestioneDati.ModelDataAccess;

import Model.GestioneDati.ModelObjects.Cittadino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CittadinoDAO {
    public Cittadino doLogin(String email, String pwd){
        try {
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from cittadino where email = ? and pwd = SHA1(?)");
            statement.setString(1,email);
            statement.setString(2,pwd);
            ResultSet r = statement.executeQuery();
            if (r.next()){
                Cittadino c = new Cittadino(r.getString(1),r.getString(2),r.getString(3),
                        r.getString(4), r.getString(5),r.getInt(6), r.getString(7),
                        r.getString(8), r.getInt(9), r.getInt(10));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
