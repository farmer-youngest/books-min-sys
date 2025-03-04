package com.marubi.security.system.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginDto implements Serializable {

    private static final long serialVersionUID = 6991838328668950336L;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
