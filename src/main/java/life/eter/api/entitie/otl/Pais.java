package life.eter.api.entitie.otl;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pais {
    private int id;
    private String nome;
    private String codigo_iso;
    private int bloco_id;
}
