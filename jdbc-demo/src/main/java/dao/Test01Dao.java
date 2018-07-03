package dao;

import entity.Test01;
import org.apache.log4j.Logger;
import utils.GreenPlumDBUtil;
import utils.ImpalaDBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class Test01Dao {

    private Logger log = Logger.getLogger(getClass());

    public List<Test01> findAllTest() throws Exception {
        Connection conn = GreenPlumDBUtil.getConnect();
        String sql = "select id,name from test01";
        PreparedStatement pre = conn.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        List<Test01> list = new ArrayList<Test01>();
        while (rs.next()) {
            Test01 t = new Test01();
            t.setId(rs.getInt("id"));
            t.setName(rs.getString("name"));
            list.add(t);
        }


        return list;
    }

    public void insertGreenPlum(int n) throws Exception {
        Connection conn = GreenPlumDBUtil.getConnect();
        String sql = "insert into test01(id,name) values(?,?)";
        PreparedStatement pre = conn.prepareStatement(sql);
        for (int i = 0; i <= n; i++) {
            pre.setString(1, "00" + i);
            pre.setString(2, "Tom" + i);
            pre.addBatch();
            if (i % 10000 == 0) {
                pre.executeBatch();
            }
        }
        pre.executeBatch();
    }


    public void insertKudo(int n) throws Exception {
        Connection conn = ImpalaDBUtil.getConnectionInstance();
        String sql = "insert into test01(id,name) values(?,?)";
        PreparedStatement pre = conn.prepareStatement(sql);
        for (int i = 0; i <= n; i++) {
            pre.setString(1, "00" + i);
            pre.setString(2, "Tom" + i);
            pre.addBatch();
            if (i % 10000 == 0) {
                pre.executeBatch();
            }
        }
        pre.executeBatch();
    }

    public void insertKudoTest(int n) throws Exception {
        Connection conn = ImpalaDBUtil.getConnectionInstance();
        String sql = "insert into test01(id,name) values(?,?)";
        String sql2 = "insert into test01(id,name) values('2222','2222')";
        String sql1 = "insert into test01(id,name) values('1111','1111')";
        PreparedStatement pre = conn.prepareStatement(sql);


        pre.setString(1, "00" + 1111);
        pre.setString(2, "Tom" + 1111);
//        pre.addBatch();


        pre.setString(1, "00" + 2222);
        pre.setString(2, "Tom" + 2222);
//        pre.addBatch();

        pre.executeBatch();
    }



}
