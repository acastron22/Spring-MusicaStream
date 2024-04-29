package musicasstream.bibliotecademusicas.domain.Banda;

import jakarta.persistence.*;
import lombok.*;
import musicasstream.bibliotecademusicas.domain.Avaliacao.AvaliacaoDeBanda;

import java.util.List;

@Table(name = "bandas")
@Entity(name = "Banda")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Banda {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private String nome;
    private String resumo; 
    
    @Enumerated(EnumType.STRING)
    private Estilo estilo;
    private Boolean excluido;

    @OneToMany(mappedBy = "banda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AvaliacaoDeBanda> avaliacoes;

    public Banda(DadosCadastroBanda dados) {
        this.nome = dados.nome();
        this.resumo = dados.resumo();
        this.estilo = dados.estilo();
        this.excluido = false;
    }


    public void atualizarBanda(DadosAtualizarBanda dados) {
        
        if(dados.resumo() != null){
        this.resumo = dados.resumo();
        }

        if(dados.estilo() != null){
            
        this.estilo = dados.estilo();
        }
    }
    
    public void excluirBanda() {
        
        this.excluido = true;
    }

    public void adicionarAvaliacao(AvaliacaoDeBanda avaliacao) {
        this.avaliacoes.add(avaliacao);
        avaliacao.setBanda(this);
    }
    
}
