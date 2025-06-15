package br.com.postechfiap.autenticacaoservice.application.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserAuthenticationFilter userAuthenticationFilter;

    public static final String [] POST_ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/users/login", //url que usaremos para fazer login
            "/users" //url que usaremos para criar um usuário
    };

    // Endpoints que requerem autenticação para serem acessados
    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
            "/users/test"
    };

    // Endpoints que só podem ser acessador por usuários com permissão de administrador
    public static final String [] ENDPOINTS_ADMIN = {
            "/users/test/administrator"
    };

    // Endpoints que só podem ser acessador por usuários com permissão de médico
    public static final String [] ENDPOINTS_MEDIC = {
            "/users/test/medic"
    };

    // Endpoints que só podem ser acessados por usuários com permissão de enfermeiro
    public static final String [] ENDPOINTS_NURSE = {
            "/users/test/nurse"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // Desativa a proteção contra CSRF usando Lambda DSL
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configura a política de sessão como stateless usando Lambda DSL
                .authorizeHttpRequests(authorize -> authorize // Habilita a autorização para as requisições HTTP usando Lambda DSL
                        .requestMatchers(HttpMethod.POST, POST_ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
                        .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMINISTRATOR") // Repare que não é necessário colocar "ROLE" antes do nome, como fizemos na definição das roles
                        .requestMatchers(ENDPOINTS_MEDIC).hasRole("MEDIC")
                        .requestMatchers(ENDPOINTS_NURSE).hasRole("NURSE")
                        .anyRequest().denyAll() //
                )
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Adiciona o filtro de autenticação de usuário que criamos, antes do filtro de segurança padrão do Spring Security

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
