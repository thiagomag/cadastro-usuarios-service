package br.com.postechfiap.autenticacaoservice.infraestructure.persistence.entities;

import br.com.postechfiap.autenticacaoservice.domain.enums.RoleNameEnum;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="roles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RoleEntity extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleNameEnum name;

}
