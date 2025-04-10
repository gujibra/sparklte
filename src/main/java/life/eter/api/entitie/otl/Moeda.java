package life.eter.api.entitie.otl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Moeda {
    private int id;
    private String descricao;
    private String pais;
}
