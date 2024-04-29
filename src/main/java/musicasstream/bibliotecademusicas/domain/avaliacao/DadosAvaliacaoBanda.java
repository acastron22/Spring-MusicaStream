package musicasstream.bibliotecademusicas.domain.avaliacao;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DadosAvaliacaoBanda(@NotNull
                                      @Min(1)
                                      @Max(10)
                                      Integer nota) {
    
}
