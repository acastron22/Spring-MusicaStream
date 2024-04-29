package musicasstream.bibliotecademusicas.domain.Avaliacao;


import jakarta.persistence.*;
import lombok.*;
import musicasstream.bibliotecademusicas.domain.Banda.Banda;

@Table(name = "bandas_avaliacao")
@Entity(name = "Banda_Avaliacao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AvaliacaoDeBanda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer nota;
    
    @Setter
    @ManyToOne
    @JoinColumn(name="banda_id")
    private Banda banda;

    public void setNota(Integer nota) {
        this.nota = nota;
    }

}
