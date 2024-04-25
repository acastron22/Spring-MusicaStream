package musicasstream.bibliotecademusicas.domain.Modelos.Banda;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BandaRepository extends JpaRepository <Banda, Long>{

    Page<Banda> findAllByExcluidoFalse(Pageable paginacao);
}
