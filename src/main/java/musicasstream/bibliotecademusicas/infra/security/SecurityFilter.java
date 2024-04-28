package musicasstream.bibliotecademusicas.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import musicasstream.bibliotecademusicas.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // o @Component é utilizado para que o spring carregue uma classe/componente genérico
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("chamando filtro");

        // o envio do token é feito pelo cabeçalho da requisão HTTP, e o cabeçalho se chama 'Authorization'
        // é desse cabeçalho então que vamos receber o token
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            // se tenho o cabeçalho, então faço a validação do token, se nao tenho, ele vai e segue o fluxo do filterChain
            var subject = tokenService.getSubject(tokenJWT);
            
            // para forçar a autenticação
            var usuario = repository.findByLogin(subject);
            // usuário está recuperado

            //vamos forçar pro string agora reconhecer e aceitar esse usuário
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, 
                    usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // agora sim o spring vai considerar que o usuário está logado
        }
        
        
        filterChain.doFilter(request, response);
        // o filter request é o meu próximo filtro q preciso chamar, da classe FilterChain, passando o request e o response
        // é dessa forma q eu garanto q está chamando o próximo filtro

    }
    private String recuperarToken(HttpServletRequest request) {

        var authorizationHeader = request.getHeader("Authorization");
        // se nao existir esse cabeçalho, é devolvido nulo, entao precisamos de um if

        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;

    }
}
