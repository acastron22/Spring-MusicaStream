package musicasstream.bibliotecademusicas.infra.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException  {
        
        var tokenJWT = recuperarToken(request);

        if (isLoginRequest(request)) {
            filterChain.doFilter(request, response);
        } else if (tokenJWT != null) {
            try {
                var subject = tokenService.getSubject(tokenJWT);
                var usuario = repository.findByLogin(subject);
                if (usuario == null) {
                    throw new RuntimeException("Usuário inexistente");
                }
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                filterChain.doFilter(request, response);
            } catch (JWTVerificationException e) {
                sendForbidenResponse(response, "Token inválido ou expirado");
            } catch(RuntimeException e) {
                sendForbidenResponse(response, "Token JWT inválido");
            }
        } else {
            sendForbidenResponse(response, "Usuário não autenticado");
        }
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

    private void sendForbidenResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 status code
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\": \"" + message + "\"}");
    }
}
