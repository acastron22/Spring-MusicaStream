package musicasstream.bibliotecademusicas.Modelos.Banda;

public record DadosDetalhamentoBanda(Long id, String nome, String resumo, Estilo estilo) {
    
    public DadosDetalhamentoBanda(Banda banda){
        this(banda.getId(), banda.getNome(), banda.getResumo(), banda.getEstilo());
    }
}
