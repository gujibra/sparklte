package life.eter;


import life.eter.impl.EtlProcess;
import org.apache.spark.sql.SparkSession;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class SparkLte {
    public static SparkSession spark;

    public static void main(String[] args) {

        spark = SparkSession.builder()
                .appName("SparkLte")
                .master("local[*]")
                .getOrCreate();

        Runnable task = () -> {
            long a = System.currentTimeMillis();
            System.out.println("iniciando processo de extração");
            EtlProcess.run();
            System.out.println("Processo de extração concluído: " + (System.currentTimeMillis() - a) + "ms");
        };

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(task, 0, 24, TimeUnit.HOURS);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            scheduler.shutdown();
            spark.stop();
        }));

    }
}
