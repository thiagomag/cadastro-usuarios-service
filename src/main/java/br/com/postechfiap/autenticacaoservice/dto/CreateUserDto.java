package br.com.postechfiap.autenticacaoservice.dto;

import br.com.postechfiap.autenticacaoservice.enums.RoleName;

import java.util.List;

public record CreateUserDto(

        String email,
        String password,
        List<RoleName> roles

) {
}
