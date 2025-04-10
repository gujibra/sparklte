package life.eter.api.entitie.dm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DmTempo {
    private long id_tempo;
    private int dia;
    private int mes;
    private int ano;
    private String dia_semana;
    private Date data;

}