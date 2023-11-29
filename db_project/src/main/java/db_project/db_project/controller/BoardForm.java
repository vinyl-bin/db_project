package db_project.db_project.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class BoardForm {

    private String title;
//    @NotEmpty(message = "작성자 이름은 필수입니다.")
//    private String userName;
    private String text;
//    @NotEmpty(message = "비밀번호는 필수입니다.")
//    private String password;

}
