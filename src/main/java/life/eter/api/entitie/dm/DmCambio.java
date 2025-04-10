package life.eter.api.entitie.dm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DmCambio {
    private long id_cambio;
    private java.sql.Date data;
    private String moeda_origem;
    private String moeda_destino;
    private BigDecimal taxa_cambio;
}