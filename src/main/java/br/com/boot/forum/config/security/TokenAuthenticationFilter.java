package br.com.boot.forum.config.security;

import br.com.boot.forum.modelo.Usuario;
import br.com.boot.forum.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getAuthorizationToken(request);
        boolean valido = tokenService.isTokenValid(token);
        log.info("token filter: {}, valido={}", token, valido);

        if(valido){
            authenticateClient(token);
        }

        filterChain.doFilter(request,response);
    }

    private void authenticateClient(String token) {

        long idUsuario = tokenService.getIdUsuario(token);

        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (!usuario.isPresent()){
            return;
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                usuario.get(), null, usuario.get().getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    private String getAuthorizationToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return  (token == null || token.isEmpty() || !token.startsWith("Bearer ")) ? null : token.substring(7, token.length());
    }
}
