package dao;

import org.apache.kudu.ColumnSchema;
import org.apache.kudu.Schema;
import org.apache.kudu.Type;
import org.apache.kudu.client.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class KuduTest01 {

    private static Logger log = Logger.getLogger(KudoSelectTest.class);
    private static final String KUDU_MASTER = "10.20.250.117:7051";
    private static String tableName = "testkudu01";

    public void kuduCreateTableTest() {
        KuduClient client = new KuduClient.KuduClientBuilder(KUDU_MASTER).build();
        try {
            List<ColumnSchema> columns = new ArrayList(2);
            columns.add(new ColumnSchema.ColumnSchemaBuilder("key", Type.STRING)
                    .key(true)
                    .build());
            columns.add(new ColumnSchema.ColumnSchemaBuilder("value", Type.STRING)
                    .build());
            List<String> rangeKeys = new ArrayList<String>();
            rangeKeys.add("key");
            Schema schema = new Schema(columns);
            client.createTable(tableName, schema,
                    new CreateTableOptions().setRangePartitionColumns(rangeKeys));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //自动提交
    public void kuduSaveTest() {
        KuduClient client = new KuduClient.KuduClientBuilder(KUDU_MASTER).build();
        try {
            KuduTable table = client.openTable(tableName);

            KuduSession session = client.newSession();
            log.info("-------start--------");
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                Insert insert = table.newInsert();
                PartialRow row = insert.getRow();
                row.addString(0, i + "");
//                row.addString(1, "aaa");
                row.addInt(1, i);

                OperationResponse operationResponse = session.apply(insert);
            }

            long endTime = System.currentTimeMillis();//记录结束时间

            float excTime = (float) (endTime - startTime) / 1000;
            log.info("耗时：" + excTime);
            log.info("-------end--------");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void insertTest() throws KuduException {
        KuduClient client = new KuduClient.KuduClientBuilder(KUDU_MASTER).build();
        KuduTable table = client.openTable(tableName);
        KuduSession session = client.newSession();

        SessionConfiguration.FlushMode mode = SessionConfiguration.FlushMode.MANUAL_FLUSH;
        session.setFlushMode(mode);
        session.setMutationBufferSpace(50000);


        log.info("-------start--------");
        long startTime = System.currentTimeMillis();
        int uncommit = 1;
        for (int i = 0; i < 1000000; i++) {
            Insert insert = table.newInsert();
            PartialRow row = insert.getRow();
            row.addString(0, i + "");
//                row.addString(1, "aaa");
            row.addInt(1, i);
//            session.flush();
            session.apply(insert);

            if (uncommit > 3000) {
                session.flush();
                uncommit = 0;
            }
            uncommit++;
        }

        if (uncommit > 0) {
            session.flush();
        }

        long endTime = System.currentTimeMillis();//记录结束时间

        float excTime = (float) (endTime - startTime) / 1000;
        log.info("耗时：" + excTime);
        log.info("-------end--------");
        session.close();
        client.close();
    }

    public void kuduUpdateTest() {

        KuduClient client = new KuduClient.KuduClientBuilder(KUDU_MASTER).build();
        try {
            KuduTable table = client.openTable(tableName);
            KuduSession session = client.newSession();
            Update update = table.newUpdate();
            PartialRow row = update.getRow();
//            row.addString("word", "100");
//            row.addString("cnt", "cnt " + 10);
            row.addInt(1,4);
            OperationResponse operationResponse = session.apply(update);

            System.out.print(operationResponse.getRowError());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
