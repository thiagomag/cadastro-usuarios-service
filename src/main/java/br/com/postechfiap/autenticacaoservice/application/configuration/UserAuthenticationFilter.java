package br.com.postechfiap.autenticacaoservice.application.configuration;

import br.com.postechfiap.autenticacaoservice.application.gatways.UserGateway;
import br.com.postechfiap.autenticacaoservice.application.service.JwtTokenService;
import br.com.postechfiap.autenticacaoservice.domain.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class UserAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService; // Service que definimos anteriormente

    private final UserGateway userGateway; // Repository que definimos anteriormente

    public static final String [] PUBLIC_ENDPOINTS = {
            "/api/swagger-ui.html", // Adicione aqui
            "/v3/api-docs/",     // Caminho para a especificação OpenAPI (JSON/YAML)
            "/swagger-ui/"       // Para recursos estáticos do Swagger UI (JS, CSS)
    };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Verifica se o endpoint requer autenticação antes de processar a requisição

        if (isPublicEndpoint(request)) {
            filterChain.doFilter(request, response); // Se for público, apenas continua a cadeia de filtros
            return;
        }

        String token = recoveryToken(request); // Recupera o token do cabeçalho Authorization da requisição
        if (token != null) {
            String subject = jwtTokenService.getSubjectFromToken(token); // Obtém o assunto (neste caso, o nome de usuário) do token
            final var user = userGateway.findByEmail(subject).get(); // Busca o usuário pelo email (que é o assunto do token)
            final var userDetails = new UserDetailsImpl(user); // Cria um UserDetails com o usuário encontrado

            // Cria um objeto de autenticação do Spring Security
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

            // Define o objeto de autenticação no contexto de segurança do Spring Security
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            throw new RuntimeException("O token está ausente.");
        }
        filterChain.doFilter(request, response); // Continua o processamento da requisição
    }

    // Recupera o token do cabeçalho Authorization da requisição
    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    // Verifica se o endpoint requer autenticação antes de processar a requisição
    private boolean isPublicEndpoint(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();

        // Verifica se o URI da requisição corresponde a qualquer um dos endpoints públicos ALL-METHOD
        boolean isPublicAllMethod = Arrays.stream(SecurityConfiguration.PUBLIC_ENDPOINTS)
                .anyMatch(pattern -> {
                    String cleanedPattern = pattern.replace("/**", "").replace("/*", "");
                    return requestURI.startsWith(cleanedPattern) || pattern.endsWith("/**") && requestURI.startsWith(pattern.substring(0, pattern.length() - 3));
                });

        // Verifica se é um POST para um endpoint POST-specificamente público
        boolean isPublicPost = requestMethod.equals("POST") &&
                Arrays.stream(SecurityConfiguration.POST_ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED)
                        .anyMatch(requestURI::startsWith);

        return isPublicAllMethod || isPublicPost;
    }

}
