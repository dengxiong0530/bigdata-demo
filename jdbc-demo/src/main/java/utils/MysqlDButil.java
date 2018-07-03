package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MysqlDButil {


    private static String DRIVERNAME = null;
    private static String URL = null;
    private static String USER = null;
    private static String PASSWORD = null;

    public static Connection conn = null;


    static {
        try {
            Properties props = new Properties();
            InputStream in = GreenPlumDBUtil.class.getClassLoader().getResourceAsStream("resource.properties");
            props.load(in);
            DRIVERNAME = props.getProperty("mysql_drivername");
            URL = props.getProperty("mysql_url");
            USER = props.getProperty("mysql_user");
            PASSWORD = props.getProperty("mysql_password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnect() throws Exception {

        if (conn != null) {
            return conn;
        }

        Class.forName(DRIVERNAME);
        conn = DriverManager.getConnection(URL, USER, PASSWORD);

        return conn;

    }


    public static void closeResource(Connection conn, PreparedStatement st) throws SQLException {
        st.close();
        conn.close();
    }

    public static void closeResource(Connection conn, ResultSet rs, PreparedStatement st) throws SQLException {
        st.close();
        rs.close();
        conn.close();
    }



}
