package musicasstream.bibliotecademusicas.Modelos.Banda;

import jakarta.persistence.*;
import lombok.*;

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
}
