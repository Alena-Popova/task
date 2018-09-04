

import java.sql.*;
import java.util.Properties;


public class SQLStorage {

    public static void main(String[] args) {
        Connection conn = null;
        String url = "jdbc:postgresql://localhost:5432/lesson_tree";
        String username = "postgres";
        String password = "root";
        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement  st = conn.prepareStatement("insert into car(name, corps_id, engine_id, gearbox_id) values(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, "Lada 5");
            st.setInt(2, 3);
            st.setInt(3, 1);
            st.setInt(4, 1);
            st.executeUpdate();
            ResultSet rk = st.getGeneratedKeys();
            if (rk.next()) {
                System.out.println(rk.getInt(1));
            }
            /*while (rs.next()) {
                System.out.println(String.format("%s %d", rs.getString("name"), rs.getInt("id")));
            }*/
            rk.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("this 1");
            System.out.println(String.format("%s %s",e.getMessage(), e.getStackTrace()));
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println(String.format("%s %s",e.getMessage(), e.getStackTrace()));
                }
            }
        }

    }
}
