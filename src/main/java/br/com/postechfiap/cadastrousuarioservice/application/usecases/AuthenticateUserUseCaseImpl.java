package br.com.postechfiap.cadastrousuarioservice.application.usecases;

import br.com.postechfiap.cadastrousuarioservice.application.interfaces.AuthenticateUserUseCase;
import br.com.postechfiap.cadastrousuarioservice.application.service.JwtTokenService;
import br.com.postechfiap.cadastrousuarioservice.domain.UserDetailsImpl;
import br.com.postechfiap.cadastrousuarioservice.infraestructure.controller.dto.LoginUserDto;
import br.com.postechfiap.cadastrousuarioservice.infraestructure.controller.dto.RecoveryJwtTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateUserUseCaseImpl implements AuthenticateUserUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    @Override
    public RecoveryJwtTokenDto execute(LoginUserDto loginUserDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }
}
