package life.eter.api.entitie.dm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DmPais {
    private long id_pais;
    private String nome;
    private String codigo_iso;
    private String bloco_economico;
}