package life.eter.impl;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;

import java.util.List;

import static life.eter.SparkLte.spark;
import static life.eter.api.util.EntityUtils.*;


public  class Extract {
    private static  final String url = "jdbc:mysql://localhost:3306/otl";
    private static final String user = "root";
    private static final String password = "1337";


    private static List<String> getTables(){
        Dataset<Row> s = spark.read()
                .format("jdbc")
                .option("url", url)
                .option("user", user)
                .option("password", password)
                .option("dbtable", "information_schema.tables")
                .load()
                .select("table_name")
                .filter("table_schema = 'otl'");

        return s.as(Encoders.STRING()).collectAsList();
    }


    private static void extractValues(String table) {
        Dataset<Row> data = spark.read()
                .format("jdbc")
                .option("url", url)
                .option("user", user)
                .option("password", password)
                .option("dbtable", table)
                .load();

        addEntitie(table, data);
    }

    public static void run(){
        for(String s : getTables()){
            extractValues(s);
        }
    }
}
