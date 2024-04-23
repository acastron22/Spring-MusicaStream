package musicasstream.bibliotecademusicas.Modelos.Bandas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BandaRepository extends JpaRepository <Banda, Long>{

    Page<Banda> findAllByExcluidoFalse(Pageable paginacao);
}
