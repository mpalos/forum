package br.com.boot.forum.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getAuthorizationToken(request);
        log.info("token filter: {}", token);

        filterChain.doFilter(request,response);
    }

    private String getAuthorizationToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return  (token == null || token.isEmpty() || !token.startsWith("Bearer ")) ? null : token.substring(7, token.length());
    }
}
