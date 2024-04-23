package musicasstream.bibliotecademusicas.Modelos.Bandas;

import javax.swing.plaf.metal.MetalDesktopIconUI;

public record DadosListagemBanda(Long id, String nome, Estilo estilo) {

    public DadosListagemBanda(Banda banda) {
       this(banda.getId(),banda.getNome(), banda.getEstilo());
    }
}
