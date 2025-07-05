package br.com.postechfiap.cadastrousuarioservice.domain.entity;

import br.com.postechfiap.cadastrousuarioservice.domain.enums.RoleNameEnum;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {

    private RoleNameEnum name;
}