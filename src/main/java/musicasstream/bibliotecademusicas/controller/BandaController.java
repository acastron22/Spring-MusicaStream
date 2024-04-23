package musicasstream.bibliotecademusicas.controller;

import musicasstream.bibliotecademusicas.Modelos.Bandas.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    public Page<DadosListagemBanda> listaDeBandas(Pageable paginacao){
        return repository.findAllByExcluidoFalse(paginacao).map(DadosListagemBanda::new);
    }
    
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody DadosAtualizarBanda dados){
        var banda = repository.getReferenceById(dados.id());
        banda.atualizarBanda(dados);
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var banda = repository.getReferenceById(id);
        
        banda.excluirBanda();
    }
}
