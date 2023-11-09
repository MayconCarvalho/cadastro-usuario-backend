package br.com.firstDecision.saveUsers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {

    private String name;
    private String email;
    private String password;
    private String confirmPassword;
}
