package db_project.db_project.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginForm {

    @NotNull
    private String name;
    @NotEmpty
    private String password;
}
