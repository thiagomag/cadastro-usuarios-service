package br.com.postechfiap.autenticacaoservice.infraestructure.persistence.entities;

import br.com.postechfiap.autenticacaoservice.domain.enums.EmployeeTypeEnum;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@Table(name = "users")
@Entity(name = "User")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserEntity extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private EmployeeTypeEnum employeeType;
    private String councilNumber;
    private String councilState;
    private String specialty;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RoleEntity roleEntity;

}