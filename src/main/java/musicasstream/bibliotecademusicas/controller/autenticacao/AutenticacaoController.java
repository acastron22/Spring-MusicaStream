package musicasstream.bibliotecademusicas.controller.autenticacao;

import jakarta.validation.Valid;
import musicasstream.bibliotecademusicas.domain.usuarios.DadosAutenticacao;
import musicasstream.bibliotecademusicas.domain.usuarios.Usuario;
import musicasstream.bibliotecademusicas.infra.security.DadosTokenJWT;
import musicasstream.bibliotecademusicas.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    
    @Autowired
    private AuthenticationManager manager;
    
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var autenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(autenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
        
    }
    
}
