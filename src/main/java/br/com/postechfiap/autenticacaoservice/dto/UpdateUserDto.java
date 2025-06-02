package br.com.postechfiap.autenticacaoservice.dto;

public record UpdateUserDto(

        Long userId,
        String email,
        String password,
        Boolean passwordChangeAuthorized,
        Boolean emailChangeAuthorized

) {
}
