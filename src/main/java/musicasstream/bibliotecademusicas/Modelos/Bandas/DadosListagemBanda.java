package musicasstream.bibliotecademusicas.Modelos.Bandas;

import javax.swing.plaf.metal.MetalDesktopIconUI;

public record DadosListagemBanda(String nome, Estilo estilo) {

    public DadosListagemBanda(Banda banda) {
       this(banda.getNome(), banda.getEstilo());
    }
}
