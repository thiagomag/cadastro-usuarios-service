package br.com.postechfiap.autenticacaoservice.infraestructure.controller.dto;

public record LoginUserDto(

        String email,
        String password

) {
}