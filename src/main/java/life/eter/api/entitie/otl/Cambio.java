package life.eter.api.entitie.otl;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cambio {
    private int id;
    private Date data;
    private int moeda_origem;
    private int moeda_destino;
    private BigDecimal taxa_cambio;
}
