package musicasstream.bibliotecademusicas.domain.Banda;

public record DadosListagemBanda(Long id, String nome, Estilo estilo) {

    public DadosListagemBanda(Banda banda) {
       this(banda.getId(),banda.getNome(), banda.getEstilo());
    }
}
