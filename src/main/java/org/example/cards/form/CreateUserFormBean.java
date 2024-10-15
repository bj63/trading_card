package org.example.cards.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserFormBean {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    private String name;

    private Integer id;

}