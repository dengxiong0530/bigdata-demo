package utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ImpalaDBUtil {

    private static Logger log = Logger.getLogger(ImpalaDBUtil.class);
    private static Connection connection = null;
    private static String IMPALA_URL = null;
    private static String JDBC_DRIVER_NAME = null;

    static {
        try {
            Properties props = new Properties();
            //Reader in = new FileReader("db.properties");
            InputStream in = GreenPlumDBUtil.class.getClassLoader().getResourceAsStream("resource.properties");
            props.load(in);
            JDBC_DRIVER_NAME = props.getProperty("JDBC_DRIVER_NAME");
            IMPALA_URL = props.getProperty("IMPALA_URL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnectionInstance() {
        try {
            Class.forName(JDBC_DRIVER_NAME);
            connection = DriverManager.getConnection(IMPALA_URL, "", "");
        } catch (ClassNotFoundException e) {
            log.error(" load class driver [" + JDBC_DRIVER_NAME + "]exception : ", e);
        } catch (SQLException e) {
            log.error("get connection exception", e);
        }
        return connection;
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
