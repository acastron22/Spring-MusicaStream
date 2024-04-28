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

        if (isLoginRequest(request)) {
            filterChain.doFilter(request, response);
        } else if (tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT);
            var usuario = repository.findByLogin(subject);
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 status code
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"message\": \"Usuário não autenticado\"}");
            return;
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

    private boolean isLoginRequest(HttpServletRequest request) {
        
        return request.getRequestURI().equals("/login");
    }
}
