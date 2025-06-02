package br.com.postechfiap.autenticacaoservice.dto;

import br.com.postechfiap.autenticacaoservice.entity.Role;

import java.util.List;

public record RecoveryUserDto(

        Long id,
        String email,
        List<Role> roles

) {
}
