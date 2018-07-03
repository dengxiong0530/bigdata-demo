package services;

import dao.KudoSelectTest;

public class KudoSelectService {

    public void KudoQueryTest(int n) {

        for (int i = 0; i < n; i++) {

            new Thread() {
                public void run() {
                    KudoSelectTest dao = new KudoSelectTest();
                    String sql = " \n" +
                            "select depart_id as depart, count(1) as people_cnt\n" +
                            "  from thousand_million\n" +
                            " where region = '华南'\n" +
                            "   and job_state = '是'\n" +
                            " group by depart_id\n" +
                            " order by depart_id;";
                    try {

                        dao.querySql(sql);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        }

    }


}



