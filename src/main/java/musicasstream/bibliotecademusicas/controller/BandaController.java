package musicasstream.bibliotecademusicas.controller;

import musicasstream.bibliotecademusicas.Modelos.Bandas.DadosCadastroBanda;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bandas")
public class BandaController {

    @PostMapping
    public void criarBanda(@RequestBody DadosCadastroBanda dados){
        
    }
}
