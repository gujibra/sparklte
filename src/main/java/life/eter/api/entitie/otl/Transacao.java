package life.eter.api.entitie.otl;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {
    private int id;
    private int tipo_id;
    private int pais_origem;
    private int pais_destino;
    private int produto_id;
    private BigDecimal valor_monetario;
    private int quantidade;
    private int transporte_id;
    private int cambio_id;
}
