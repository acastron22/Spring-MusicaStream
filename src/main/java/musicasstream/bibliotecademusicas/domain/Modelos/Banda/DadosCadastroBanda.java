package musicasstream.bibliotecademusicas.domain.Modelos.Banda;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroBanda(
        @NotBlank(message = "Nome da banda é obrigatório")
        String nome, 
        @NotBlank (message = "Resumo da banda é obrigatório")
        String resumo,
        @NotNull(message = "estilo é obrigatório")
        Estilo estilo) {
}
