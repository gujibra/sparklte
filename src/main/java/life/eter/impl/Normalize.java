package life.eter.impl;

import life.eter.api.entitie.dm.*;
import life.eter.api.entitie.otl.Cambio;
import life.eter.api.entitie.otl.Pais;
import life.eter.api.entitie.otl.Transacao;
import life.eter.api.util.CurrencyUtil;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static life.eter.api.util.EntityUtils.*;

public class Normalize {

    public static void run(){
        normalizeCambio();
        normalizePais();
        normalizeTransacao();
        normalizeLancamento();
    }

    private static String getDiaSemana(Date data){
        String[] dias = {"domingo", "segunda", "terça", "quarta", "quinta", "sexta", "sábado"};
        return dias[data.getDay()];
    }

    private static void normalizeCambio(){
        List<DmCambio> c = new ArrayList<>(List.of());
        List<DmTempo> d = new ArrayList<>(List.of());

        for(Cambio cambio : cambios){
            Date data = cambio.getData();
            DmTempo dmTempo = new DmTempo(
                    cambio.getId(),
                    data.getDate(),
                    data.getMonth() + 1,
                    data.getYear() + 1900,
                    getDiaSemana(data),
                    data
            );

            float currency = CurrencyUtil.getExchangeRate(data, moedas.get(cambio.getMoeda_origem() -1 ).getPais(), moedas.get(cambio.getMoeda_destino() -1 ).getPais());

            DmCambio dmCambio = new DmCambio(
                    cambio.getId(),
                    data,
                    removeLastWord(moedas.get(cambio.getMoeda_origem() -1 ).getDescricao()).toUpperCase(),
                    removeLastWord(moedas.get(cambio.getMoeda_destino() -1 ).getDescricao()).toUpperCase(),
                    currency == -1f ? cambio.getTaxa_cambio() : BigDecimal.valueOf(currency)
            );
            c.add(dmCambio);
            d.add(dmTempo);

        }
        dmTempos.addAll(d);
        dmCambios.addAll(c);
    }

    private static void normalizeTransacao(){
        List<DmTransacao> t = new ArrayList<>(List.of());

        for(Transacao transacao : transacoes){
            DmTransacao dmTransacao = new DmTransacao(
                    transacao.getId(),
                    paises.get(transacao.getPais_origem() - 1).getNome().toUpperCase(),
                    paises.get(transacao.getPais_destino() - 1).getNome().toUpperCase(),
                    produtos.get(transacao.getProduto_id() - 1).getDescricao().toUpperCase(),
                    transacao.getValor_monetario().doubleValue(),
                    tipoTransacaos.get(transacao.getTipo_id() - 1).getDescricao(),
                    transacao.getQuantidade(),
                    transportes.get(transacao.getTransporte_id() - 1).getDescricao().toUpperCase()
            );
            t.add(dmTransacao);
        }
        dmTransacoes.addAll(t);

    }

    private static void normalizePais(){
        List<DmPais> p = new ArrayList<>(List.of());

        for(Pais pais : paises){
            DmPais dmPais = new DmPais(
                    pais.getId(),
                    pais.getNome().toUpperCase(),
                    pais.getCodigo_iso().toUpperCase(),
                    blocosEconomicos.get(pais.getBloco_id() - 1).getNome().toUpperCase()
            );
            p.add(dmPais);
        }
        dmPaises.addAll(p);
    }

    private static void normalizeLancamento() {
        List<FtLancamento> lancamentos = new ArrayList<>();

        for (Transacao transacao : transacoes) {
            int cambioId = transacao.getCambio_id();
            int paisOrigemId = transacao.getPais_origem();
            int paisDestinoId = transacao.getPais_destino();

            FtLancamento ftLancamento = new FtLancamento(
                    null,
                    cambioId,
                    paisOrigemId,
                    paisDestinoId,
                    dmTempos.get(cambioId - 1).getId_tempo(),
                    transacao.getId()
            );

            lancamentos.add(ftLancamento);
        }

        ftLancamentos.addAll(lancamentos);
    }


    private static String removeLastWord(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        int lastSpaceIndex = input.lastIndexOf(' ');
        if (lastSpaceIndex == -1) {
            return input;
        }
        return input.substring(0, lastSpaceIndex);
    }
}
