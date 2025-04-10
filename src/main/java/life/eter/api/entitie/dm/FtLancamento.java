package life.eter.api.entitie.dm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FtLancamento {
    @Nullable
    private Long vt_lancamento;

    private long id_cambio;
    private long id_pais_origem;
    private long id_pais_destino;
    private long id_tempo;
    private long id_transacao;
}