package br.com.postechfiap.cadastrousuarioservice.application.service;

import br.com.postechfiap.cadastrousuarioservice.application.gatways.UserGateway;
import br.com.postechfiap.cadastrousuarioservice.domain.UserDetailsImpl;
import br.com.postechfiap.cadastrousuarioservice.infraestructure.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserGateway userGateway;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userGateway.findByEmail(username).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return new UserDetailsImpl(userEntity);
    }

}
