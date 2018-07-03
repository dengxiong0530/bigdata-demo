import org.apache.log4j.Logger;
import utils.DateUtil;
import utils.ImpalaDBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class Application {
    private static Logger log = Logger.getLogger(Application.class);


    public static void main(String[] args) throws Exception {

//        DimDotInfoDao dimDotInfoDao = new DimDotInfoDao();
//
//        List<DimDotInfo> dimDotInfoList = dimDotInfoDao.getDimDotInfo();
//        log.info(DateUtil.getTime() + "===== 开始写入KUDO");
//        dimDotInfoDao.insertKudo(dimDotInfoList);
//        log.info(DateUtil.getTime() + "===== KUDO写入完成");
//        log.info(DateUtil.getTime() + "===== 开始写入GREENPLUM");
//        dimDotInfoDao.insertGreenPlum(dimDotInfoList);
//        log.info(DateUtil.getTime() + "===== GREENPLUM写入完成");

//        log.info(DateUtil.getTime() + "===== 开始KUDO delete");
//        dimDotInfoDao.deleteKudu();
//        log.info(DateUtil.getTime() + "===== 结束KUDO delete");

//        log.info(DateUtil.getTime() + "===== 开始GREENPLUM delete");
//        dimDotInfoDao.deleteGp();
//        log.info(DateUtil.getTime() + "===== 结束GREENPLUM delete");

//        Test01Dao test01Dao = new Test01Dao();
//        log.info(DateUtil.getTime() + "===== 开始写入KUDO");
//        test01Dao.insertKudo(200000);
//        log.info(DateUtil.getTime() + "===== KUDO写入完成");
//        log.info(DateUtil.getTime() + "===== 开始写入GREENPLUM");
//        test01Dao.insertGreenPlum(200000);
//        log.info(DateUtil.getTime() + "===== GREENPLUM写入完成");
//        test01Dao.insertKudoTest(1);


//        FactKpiIndexScoreDao factKpiIndexScoreDao = new FactKpiIndexScoreDao();
//        List<FactKpiIndexScore> list = factKpiIndexScoreDao.getFactKpiIndexScore();
//
//        log.info(DateUtil.getTime() + "===== 开始写入KUDO");
//        factKpiIndexScoreDao.insertKudo(list);
//        log.info(DateUtil.getTime() + "===== KUDO写入完成");
//
//        log.info(DateUtil.getTime() + "===== 开始写入GREENPLUM");
//        factKpiIndexScoreDao.insertGreenPlum(list);
//        log.info(DateUtil.getTime() + "===== GREENPLUM写入完成");


//        EmployeeInfoDao employeeInfoDao = new EmployeeInfoDao();
//        List<EmployeeInfo> employeeInfos = employeeInfoDao.getDimDotInfo();
//
//        log.info(DateUtil.getTime() + "===== 开始写入KUDO");
//        employeeInfoDao.insertKudo(employeeInfos);
//        log.info(DateUtil.getTime() + "===== KUDO写入完成");
//
//        log.info(DateUtil.getTime() + "===== 开始写入GREENPLUM");
//        employeeInfoDao.insertGreenPlum(employeeInfos);
//        log.info(DateUtil.getTime() + "===== GREENPLUM写入完成");


//        KudoSelectTest dao = new KudoSelectTest();
//        String sql = " select  count(1) as cut\n" +
//                " from fact_kpi_index_score\n" +
//                " where people_no = '061495'";
//        dao.querySql(sql);


//        KudoSelectService s = new KudoSelectService();
//        s.KudoQueryTest(20);
//        sleep(10000);
//        Map<String, Float> a = KudoSelectTest.excTimes;
//        System.out.println(a.size());
//        float all = (float) 0;
//        for(String sa:a.keySet()){
//            all = all + a.get(sa);
//            System.out.println(sa + ":" + a.get(sa));
//        }
//        float avg = all / a.size();
//        System.out.println("平均耗时：" + avg);


//        Test01Dao test01Dao = new Test01Dao();
//        test01Dao.insertKudoTest(2);

//        int n = 100;
//        for(int i = 0; i <= n;i++){
////            System.out.println(i);
//            new Thread(){
//                @Override
//                public void run() {
//                    Connection conn = ImpalaDBUtil.getConnectionInstance();
//                    System.out.println(DateUtil.getTime() + "==开启" + conn);
//                    try {
//                        sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    try {
//                        conn.close();
//                        System.out.println(DateUtil.getTime() + "==结束" + conn);
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }.start();
//        }

//        KuduTest01 t = new KuduTest01();
//        t.kuduSaveTest();
////        t.kuduCreateTableTest();
//        t.insertTest();
//        t.kuduUpdateTest();




    }


}

//
//
//class TestThread implements  Runnable{
//
//
//    private Thread t;
//    private String threadName;
//
//    public TestThread(String name ) {
//        threadName = name;
//        System.out.println("Creating " +  threadName );
//    }
//
//    public void run() {
//        System.out.println(DateUtil.getTime() + "开启：" + threadName );
//        try {
//            sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(DateUtil.getTime() + "结束：" + threadName);
//    }
//
//    public void start () {
//        System.out.println("Starting " +  threadName );
//        if (t == null) {
//            t = new Thread (this, threadName);
//            t.start ();
//        }
//    }
//}
