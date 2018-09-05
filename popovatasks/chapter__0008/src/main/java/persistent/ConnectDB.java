package persistent;

import logic.User;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectDB implements AutoCloseable {

    private Properties properties;
    private Connection connection;

    private final String url = "jdbc:postgresql://localhost/";
    private final String driver = "postgresql";
    private final String database = "jdbc:postgresql://localhost/";
    private final String tablename = "users";
    private final String databasename = "webapp";
    private final String username = "postgres";
    private final String password = "root";

    static String configuration = "/app.properties";

    public ConnectDB() {
    }

    /**
     * подключается к базе дынных. если ее нет.то создает базу данных и таблицу
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean connect() throws ClassNotFoundException {
        try {
            connection = DriverManager.getConnection(url + databasename, username, password);
        } catch (SQLException e0) {
            try {
                this.create();
                System.out.println("create");
            } catch (SQLException e1) {
                System.out.println("no create");
                throw new RuntimeException(e1);
            }
        }
        return connection != null;
    }

    /**
     * сохдает базу данных
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private boolean create() throws SQLException, ClassNotFoundException {
        boolean result = false;
        // You'll need a PostgreSQL account with 'create database' privileges.
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url + "postgres", username, password);
        Statement statement = connection.createStatement();
        result = statement.execute("CREATE DATABASE " + databasename);
        // Now close the default DB so that we can connect to the new DB.
        connection.close();
        this.connect();
        this.createTable();
        return result;
    }

    /**
     * удаляет базу данных
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void drop() throws SQLException, ClassNotFoundException {
        connection = DriverManager.getConnection(url + databasename, username, password);
        Statement statement = connection.createStatement();
        statement.execute("drop DATABASE " + databasename);
        // Now close the default DB so that we can connect to the new DB.
        connection.close();
    }


    /**
     * создает таблицу
     *
     * @throws SQLException
     */
    private void createTable() throws SQLException {
        String createTable = "CREATE TABLE IF NOT EXISTS " + tablename + " ( "
                + "                id       serial primary key,"
                + "                name varchar(200),"
                + "                login varchar(200),"
                + "                email varchar(200),"
                + "                cd       TIMESTAMP )";
        try (Statement st = connection.createStatement()) {
            st.execute(createTable);
        }
    }

    /**
     * вставляет данные
     *
     * @param positions
     */
    public void insert(List<User> positions) {
        String query = String.format("INSERT INTO %s (id, name, login, email, cd) VALUES (?, ?, ?, ?, ?)", tablename);
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            for (User position : positions) {
                prepared.setInt(1, position.getId());
                prepared.setString(2, position.getName());
                prepared.setString(3, position.getLogin());
                prepared.setString(4, position.getEmail());
                prepared.setTimestamp(5, new Timestamp(position.getCreateDate().getTime()));
                prepared.executeUpdate();
            }
        } catch (SQLException exception) {
            if (!"23505".equals(exception.getSQLState())) {
                throw new RuntimeException(exception);
            }
        }
    }

    /**
     * выгружает n значений
     *
     * @param n
     * @return
     * @throws SQLException
     */
    public List<User> getAllFirstN(int n) throws SQLException {
        List<User> result = new ArrayList<>();
        String query = String.format("select * from %s", tablename);
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next() && n > 0) {
                result.add(new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("email"),
                        new Date(rs.getTimestamp("cd").getTime())));
                n--;
            }
        }
        return result;
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static Properties loadProperties() {
        Properties result = new Properties();
        try {
            result.load(new FileReader(configuration));
            System.out.println("load ok");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }
}