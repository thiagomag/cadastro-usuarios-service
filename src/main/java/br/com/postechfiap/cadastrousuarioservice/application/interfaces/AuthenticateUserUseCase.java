package br.com.postechfiap.cadastrousuarioservice.application.interfaces;

import br.com.postechfiap.cadastrousuarioservice.infraestructure.controller.dto.LoginUserDto;
import br.com.postechfiap.cadastrousuarioservice.infraestructure.controller.dto.RecoveryJwtTokenDto;

public interface AuthenticateUserUseCase extends UseCase<LoginUserDto, RecoveryJwtTokenDto> {
}
