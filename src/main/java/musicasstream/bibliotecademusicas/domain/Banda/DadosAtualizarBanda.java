package musicasstream.bibliotecademusicas.domain.Banda;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarBanda(
        @NotNull
        Long id, 
        
        String resumo, 
        
        Estilo estilo) {
}
