package br.com.postechfiap.autenticacaoservice.domain.entity;

import br.com.postechfiap.autenticacaoservice.domain.enums.EmployeeTypeEnum;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private EmployeeTypeEnum employeeTypeEnum;
    private String councilNumber;
    private String councilState;
    private String specialty;
    private Boolean passwordChangeAuthorized;
    private Boolean emailChangeAuthorized;

    public Boolean isPasswordChangeAuthorized() {
        return passwordChangeAuthorized != null && passwordChangeAuthorized;
    }

    public Boolean isEmailChangeAuthorized() {
        return emailChangeAuthorized != null && emailChangeAuthorized;
    }
}


