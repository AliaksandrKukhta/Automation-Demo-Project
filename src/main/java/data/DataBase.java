package data;

import model.User;
import org.apache.log4j.Logger;

import java.sql.*;

public class DataBase {
    private static final Logger logger = Logger.getLogger(DataBase.class);

    public String selectUser(Name select) {
        return String.format("SELECT * FROM user.user_info where name='%s'", select);
    }

    public void fillingInUser(User someUser, String aaa) {
        String url = "jdbc:mysql://localhost:3306/user?useSSL=false&useUnicode=true&serverTimezone=UTC";
        ;
        String user = "********";
        String password = "**********";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            logger.info("Connect to data");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(selectUser(Name.KUKHTA));
            while (rs.next()) {
                someUser.setId(Integer.parseInt(rs.getString(1)));
                someUser.setName(rs.getString(2));
                someUser.setLogin(rs.getString(3));
                someUser.setPassword(rs.getString(4));
                someUser.setE_mail(rs.getString(5));
            }
        } catch (SQLException ex) {
            logger.error("No database connection");
            ex.printStackTrace();
        }
    }
}
