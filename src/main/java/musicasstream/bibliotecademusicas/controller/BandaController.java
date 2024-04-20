package musicasstream.bibliotecademusicas.controller;

import musicasstream.bibliotecademusicas.Modelos.Bandas.Banda;
import musicasstream.bibliotecademusicas.Modelos.Bandas.BandaRepository;
import musicasstream.bibliotecademusicas.Modelos.Bandas.DadosCadastroBanda;

import musicasstream.bibliotecademusicas.Modelos.Bandas.Estilo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bandas")
public class BandaController {
    
    @Autowired
    private BandaRepository repository;

    @PostMapping
    public void criarBanda(@RequestBody DadosCadastroBanda dados){
         
        repository.save(new Banda(dados));
    }
}
