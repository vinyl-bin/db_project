package db_project.db_project.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class BoardForm {

    private String title;
    private String text;
    private String fileName;
    private String filePath;
    private MultipartFile fileSave;

}
