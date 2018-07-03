package utils;

import org.apache.kudu.client.KuduClient;
import org.apache.kudu.client.KuduException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class KudoUtil {


    private static String kudu_master = null;
    static {
        try {
            Properties props = new Properties();
            //Reader in = new FileReader("db.properties");
            InputStream in = GreenPlumDBUtil.class.getClassLoader().getResourceAsStream("resource.properties");
            props.load(in);
            kudu_master = props.getProperty("KUDU_MASTER");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static KuduClient getKuduClient(){
//        KuduClient client = new KuduClient.KuduClientBuilder(kudu_master).build();
        return new KuduClient.KuduClientBuilder(kudu_master).build();
    }

    public static void shotdownKuduClient( KuduClient client){

        try {
            client.shutdown();
        } catch (KuduException e) {
            e.printStackTrace();
        }
    }


}
