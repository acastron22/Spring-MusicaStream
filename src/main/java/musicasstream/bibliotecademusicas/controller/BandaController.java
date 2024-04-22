package musicasstream.bibliotecademusicas.controller;

import musicasstream.bibliotecademusicas.Modelos.Bandas.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bandas")
public class BandaController {
    
    @Autowired
    private BandaRepository repository;

    @PostMapping
    @Transactional
    public void criarBanda(@RequestBody DadosCadastroBanda dados){
         
        repository.save(new Banda(dados));
    }
    
    @GetMapping
    public Page<DadosListagemBanda> listaDeBandas(@PageableDefault(size = 1) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemBanda::new);
    }
}
