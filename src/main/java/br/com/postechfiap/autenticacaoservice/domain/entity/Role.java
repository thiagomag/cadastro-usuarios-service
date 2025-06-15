package br.com.postechfiap.autenticacaoservice.domain.entity;

import br.com.postechfiap.autenticacaoservice.domain.enums.RoleNameEnum;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {

    private RoleNameEnum name;
}