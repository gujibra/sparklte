package life.eter.api.util;

import life.eter.api.entitie.dm.*;
import org.apache.spark.sql.Dataset;
import life.eter.api.entitie.otl.*;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;

import java.util.ArrayList;
import java.util.List;

public class EntityUtils {
    //DM
    public static List<FtLancamento> ftLancamentos = new ArrayList<>();
    public static List<DmTempo> dmTempos = new ArrayList<>();
    public static List<DmPais> dmPaises = new ArrayList<>();
    public static List<DmTransacao> dmTransacoes = new ArrayList<>();
    public static List<DmCambio> dmCambios = new ArrayList<>();

    //OTL
    public static  List<BlocoEconomico> blocosEconomicos = new ArrayList<>();
    public static List<Cambio> cambios = new ArrayList<>();
    public static List<Moeda> moedas = new ArrayList<>();
    public static List<Pais> paises = new ArrayList<>();
    public static List<Produto> produtos = new ArrayList<>();
    public static List<Transacao> transacoes = new ArrayList<>();
    public static List<Transporte> transportes = new ArrayList<>();
    public static List<TipoTransacao> tipoTransacaos = new ArrayList<>();
    public static List<CategoriaProduto> categoriaProduto = new ArrayList<>();


    public static void reset(){
        ftLancamentos.clear();
        dmTempos.clear();
        dmPaises.clear();
        dmTransacoes.clear();
        dmCambios.clear();

        blocosEconomicos.clear();
        cambios.clear();
        moedas.clear();
        paises.clear();
        produtos.clear();
        transacoes.clear();
        transportes.clear();
        tipoTransacaos.clear();
        categoriaProduto.clear();
    }

    public static void addEntitie(String table, Dataset<Row> data){
        switch (table) {
            case "blocos_economicos":
                List<BlocoEconomico> blocos = data.as(Encoders.bean(BlocoEconomico.class)).collectAsList();
                blocosEconomicos.addAll(blocos);
                System.out.println("Blocos economicos salvos ");
                break;
            case "cambios":
                List<Cambio> cambiosList = data.as(Encoders.bean(Cambio.class)).collectAsList();
                cambios.addAll(cambiosList);
                System.out.println("Cambios salvos ");
                break;
            case "moedas":
                List<Moeda> moedasList = data.as(Encoders.bean(Moeda.class)).collectAsList();
                moedas.addAll(moedasList);
                System.out.println("Moedas salvos ");
                break;
            case "paises":
                List<Pais> paisesList = data.as(Encoders.bean(Pais.class)).collectAsList();
                paises.addAll(paisesList);
                System.out.println("Paises salvos ");
                break;
            case "produtos":
                List<Produto> produtosList = data.as(Encoders.bean(Produto.class)).collectAsList();
                produtos.addAll(produtosList);
                System.out.println("Produtos salvos ");
                break;
            case "transacoes":
                List<Transacao> transacoesList = data.as(Encoders.bean(Transacao.class)).collectAsList();
                transacoes.addAll(transacoesList);
                System.out.println("Transações salvas ");
                break;
            case "transportes":
                List<Transporte> transportesList = data.as(Encoders.bean(Transporte.class)).collectAsList();
                transportes.addAll(transportesList);
                System.out.println("Transportes salvos ");
                break;
            case "tipos_transacoes":
                List<TipoTransacao> tipoTransacaosList = data.as(Encoders.bean(TipoTransacao.class)).collectAsList();
                tipoTransacaos.addAll(tipoTransacaosList);
                System.out.println("Tipo de transições salvos ");
                break;
            case "categoria_produtos":
                List<CategoriaProduto> categoriaProdutosList = data.as(Encoders.bean(CategoriaProduto.class)).collectAsList();
                categoriaProduto.addAll(categoriaProdutosList);
                System.out.println("Categorias do produto salvos ");
                break;
            default:
                System.out.println("table invalida: " + table);
        }

    }


}
