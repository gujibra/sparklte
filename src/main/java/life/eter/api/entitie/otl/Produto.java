package life.eter.api.entitie.otl;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    private int id;
    private String descricao;
    private int categoria_id;
    private String codigo_ncm;
}
