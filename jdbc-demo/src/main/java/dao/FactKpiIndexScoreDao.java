package dao;

import entity.FactKpiIndexScore;
import utils.GreenPlumDBUtil;
import utils.ImpalaDBUtil;
import utils.MysqlDButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FactKpiIndexScoreDao {


    public List<FactKpiIndexScore> getFactKpiIndexScore() throws Exception {
        Connection conn = MysqlDButil.getConnect();
        String sql = "SELECT stat_mon,\n" +
                "       stat_type,\n" +
                "       people_no ,\n" +
                "       dot_code,\n" +
                "       quality_score\n" +
                "FROM fact_kpi_index_score";

        PreparedStatement pre = conn.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        List<FactKpiIndexScore> list = new ArrayList<FactKpiIndexScore>();
        while (rs.next()) {
            FactKpiIndexScore t = new FactKpiIndexScore(rs.getString("stat_mon"),
                    rs.getString("stat_type"),
                    rs.getString("people_no"),
                    rs.getString("dot_code"),
                    rs.getString("quality_score"));

            list.add(t);
        }
        return list;
    }

    public void insertKudo(List<FactKpiIndexScore> list) throws SQLException {

        Connection conn = ImpalaDBUtil.getConnectionInstance();
        String sql = "insert into fact_kpi_index_score(rowkey,stat_mon,stat_type,people_no,dot_code,quality_score) values(?,?,?,?,?,?)";
        PreparedStatement pre = conn.prepareStatement(sql);
//        int i = 0;

        for (int i = 0; i < 100; i++) {


            for (FactKpiIndexScore d : list) {
                pre.setString(1, d.getStatMon() + d.getStatType() + d.getPeopleNo() + d.getDotCode() + i);
                pre.setString(2, d.getStatMon());
                pre.setString(3, d.getStatType());
                pre.setString(4, d.getPeopleNo());
                pre.setString(5, d.getDotCode());
                pre.setString(6, d.getQualityScore());

                pre.execute();
            }
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

    public void insertGreenPlum(List<FactKpiIndexScore> list) throws Exception {
        Connection conn = GreenPlumDBUtil.getConnect();
        String sql = "insert into fact_kpi_index_score(stat_mon,stat_type,people_no,dot_code,quality_score) values(?,?,?,?,?)";
        PreparedStatement pre = conn.prepareStatement(sql);
        int i = 0;
        for (FactKpiIndexScore d : list) {
            pre.setString(1, d.getStatMon());
            pre.setString(2, d.getStatType());
            pre.setString(3, d.getPeopleNo());
            pre.setString(4, d.getDotCode());
            pre.setString(5, d.getQualityScore());

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
