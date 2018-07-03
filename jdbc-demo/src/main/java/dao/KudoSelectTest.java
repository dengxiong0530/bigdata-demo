package dao;

import org.apache.log4j.Logger;
import utils.ImpalaDBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class KudoSelectTest {

 public static Map<String,Float> excTimes = new HashMap<String, Float>();
    private static Logger log = Logger.getLogger(KudoSelectTest.class);

    public void querySql(String sql) throws Exception {

        Connection conn = ImpalaDBUtil.getConnectionInstance();
        log.info("开启一个连接：" + conn);
        long startTime=System.currentTimeMillis();
        PreparedStatement pre = conn.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        String s = "";
        while (rs.next()) {
            s = rs.getString("people_cnt");
            break;
        }

//        s = rs.getString("people_cnt");

        long endTime=System.currentTimeMillis();//记录结束时间

        float excTime=(float)(endTime-startTime)/1000;
        log.info("连接：" + conn + "耗时：" + excTime);
        excTimes.put(conn.toString(),excTime);
        log.info("连接：" + conn + "结果：" + s);

        rs.close();
        pre.close();
        conn.close();
    }
}
