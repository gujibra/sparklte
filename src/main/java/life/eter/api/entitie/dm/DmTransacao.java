package life.eter.api.entitie.dm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DmTransacao {
    private long id_transacao;
    private String pais_origem;
    private String pais_destino;
    private String produto;
    private double valor;
    private String tipo_transacao;
    private int quantidade;
    private String transporte;
}