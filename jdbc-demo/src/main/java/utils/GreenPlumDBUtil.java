package utils;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class GreenPlumDBUtil {

    private static String DRIVERNAME = null;
    private static String URL = null;
    private static String USER = null;
    private static String PASSWORD = null;

    public static Connection conn = null;


    static {
        try {
            Properties props = new Properties();
            //Reader in = new FileReader("db.properties");
            InputStream in = GreenPlumDBUtil.class.getClassLoader().getResourceAsStream("resource.properties");
            props.load(in);
            DRIVERNAME = props.getProperty("drivername");
            URL = props.getProperty("url");
            USER = props.getProperty("user");
            PASSWORD = props.getProperty("password");
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
