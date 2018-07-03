package dao;

import entity.DimDotInfo;
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

public class DimDotInfoDao {

    private static Logger log = Logger.getLogger(DimDotInfoDao.class);

    public List<DimDotInfo> getDimDotInfo() throws Exception {
        Connection conn = MysqlDButil.getConnect();
        String sql = "select * from dim_dot_info";

        PreparedStatement pre = conn.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        List<DimDotInfo> list = new ArrayList<DimDotInfo>();
        while (rs.next()) {
            DimDotInfo t = new DimDotInfo(rs.getString("dot_code"),
                    rs.getString("dot_name"),
                    rs.getString("operation_dept_code"),
                    rs.getString("operation_dept_name"),
                    rs.getString("region_code"),
                    rs.getString("region_name"),
                    rs.getString("etl_update_time"));

            list.add(t);
        }
        return list;
    }


    public void insertKudo(List<DimDotInfo> list) throws SQLException {

        Connection conn = ImpalaDBUtil.getConnectionInstance();
        String sql = "insert into dim_dot_info(dot_code,dot_name,operation_dept_code,operation_dept_name,region_code,region_name,etl_update_time) values(?,?,?,?,?,?,?)";
        PreparedStatement pre = conn.prepareStatement(sql);
        int i = 0;
        for (DimDotInfo d : list) {
            pre.setString(1, d.getDotCode());
            pre.setString(2, d.getDotName());
            pre.setString(3, d.getOperationDeptCode());
            pre.setString(4, d.getGetOperationDeptCodeName());
            pre.setString(5, d.getRegionCode());
            pre.setString(6, d.getRegionName());
            pre.setString(7, d.getEtlUpdateTime());
//            pre.execute();
            pre.addBatch();

            if (i == 10000) {
                pre.executeBatch();
                i = 0;
            }
        }
        pre.executeBatch();

    }

    public void insertGreenPlum(List<DimDotInfo> list) throws Exception {
        Connection conn = GreenPlumDBUtil.getConnect();
        String sql = "insert into dim_dot_info(dot_code,dot_name,operation_dept_code,operation_dept_name,region_code,region_name,etl_update_time) values(?,?,?,?,?,?,?)";
        PreparedStatement pre = conn.prepareStatement(sql);
        int i = 0;
        for (DimDotInfo d : list) {
            pre.setString(1, d.getDotCode());
            pre.setString(2, d.getDotName());
            pre.setString(3, d.getOperationDeptCode());
            pre.setString(4, d.getGetOperationDeptCodeName());
            pre.setString(5, d.getRegionCode());
            pre.setString(6, d.getRegionName());
            pre.setString(7, d.getEtlUpdateTime());
            pre.addBatch();

            if (i == 10000) {
                pre.executeBatch();
                i = 0;
            }
        }
        pre.executeBatch();
    }

    public void deleteKudu() throws SQLException {
        Connection conn = ImpalaDBUtil.getConnectionInstance();
        String sql = "delete from dim_dot_info";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.executeUpdate();
    }


    public void deleteGp() throws Exception {

        Connection conn = GreenPlumDBUtil.getConnect();
        String sql = "delete from dim_dot_info";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.executeUpdate();
    }



}
