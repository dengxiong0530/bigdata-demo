package dao;

import entity.EmployeeInfo;
import org.apache.log4j.Logger;
import utils.GreenPlumDBUtil;
import utils.ImpalaDBUtil;
import utils.MysqlDButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeInfoDao {

    private static Logger log = Logger.getLogger(DimDotInfoDao.class);

    public List<EmployeeInfo> getDimDotInfo() throws Exception {
        Connection conn = MysqlDButil.getConnect();
        String sql = "SELECT people_no,\n" +
                "       people_name,\n" +
                "       dot ,\n" +
                "       in_date\n" +
                "FROM dim_employee_info";

        PreparedStatement pre = conn.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        List<EmployeeInfo> list = new ArrayList<EmployeeInfo>();
        while (rs.next()) {
            EmployeeInfo t = new EmployeeInfo(rs.getString("people_no"),
                    rs.getString("people_name"),
                    rs.getString("dot"),
                    rs.getString("in_date"));
            list.add(t);
        }
        return list;
    }

    public void insertKudo(List<EmployeeInfo> list) throws SQLException {

        Connection conn = ImpalaDBUtil.getConnectionInstance();
        String sql = "insert into dim_employee_info(people_no,people_name,dot,in_date) values(?,?,?,?)";
        PreparedStatement pre = conn.prepareStatement(sql);
//        int i = 0;

            for (EmployeeInfo d : list) {
                pre.setString(1, d.getPeopleNo());
                pre.setString(2, d.getPeopleName());
                pre.setString(3, d.getDot());
                pre.setString(4, d.getInDate());

                pre.execute();
            }
//            pre.addBatch();
//
//            if (i == 10000) {
//                pre.executeBatch();
//                i = 0;
//            }
//        }
//        pre.executeBatch();

    }

    public void insertGreenPlum(List<EmployeeInfo> list) throws Exception {
        Connection conn = GreenPlumDBUtil.getConnect();
        String sql = "insert into dim_employee_info(people_no,people_name,dot,in_date) values(?,?,?,?)";
        PreparedStatement pre = conn.prepareStatement(sql);
        int i = 0;
        for (EmployeeInfo d : list) {
            pre.setString(1, d.getPeopleNo());
            pre.setString(2, d.getPeopleName());
            pre.setString(3, d.getDot());
            pre.setString(4, d.getInDate());

            pre.addBatch();

            if (i == 10000) {
                pre.executeBatch();
                i = 0;
            }
            i++;
        }

        pre.executeBatch();

    }
}
