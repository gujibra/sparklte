package life.eter.impl;

import com.amazonaws.services.comprehend.model.transform.EntityJsonUnmarshaller;
import life.eter.api.entitie.dm.*;
import life.eter.api.util.EntityUtils;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;

import java.util.List;

import static life.eter.api.util.EntityUtils.*;

public class EtlProcess {
    public static void run(){
        Extract.run();
        Normalize.run();

        insertItems(DmCambio.class, dmCambios, "dm_cambio");
        insertItems(DmPais.class, dmPaises, "dm_pais");
        insertItems(DmTempo.class, dmTempos, "dm_tempo");
        insertItems(DmTransacao.class, dmTransacoes, "dm_transacao");
        insertItems(FtLancamento.class, ftLancamentos, "ft_lancamento");

        EntityUtils.reset();
    }

    public static <T> void insertItems(Class<T> clazz, List<T> items, String tableName) {
        SparkSession spark = SparkSession.builder().getOrCreate();
        Dataset<T> df = spark.createDataset(items, Encoders.bean(clazz));

        df.write()
                .format("jdbc")
                .option("url", "jdbc:mysql://root:MgpsFDPiSUjyAnxvabvbkrVNaqKDDqOh@caboose.proxy.rlwy.net:11318/railway")
                .option("dbtable", tableName)
                .option("user", "root")
                .option("password", "MgpsFDPiSUjyAnxvabvbkrVNaqKDDqOh")
                .mode("append")
                .save();
    }


}
