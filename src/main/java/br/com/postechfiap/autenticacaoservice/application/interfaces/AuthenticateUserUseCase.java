package br.com.postechfiap.autenticacaoservice.application.interfaces;

import br.com.postechfiap.autenticacaoservice.infraestructure.controller.dto.LoginUserDto;
import br.com.postechfiap.autenticacaoservice.infraestructure.controller.dto.RecoveryJwtTokenDto;

public interface AuthenticateUserUseCase extends UseCase<LoginUserDto, RecoveryJwtTokenDto> {
}
