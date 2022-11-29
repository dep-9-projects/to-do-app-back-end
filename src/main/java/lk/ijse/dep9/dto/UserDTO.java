package lk.ijse.dep9.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    @NotBlank(message = "User Name Cannot be empty")
    @Pattern(regexp = "[A-Za-z0-9 ]+",message = "Invalid user name")
    String userName;
    @NotBlank(message = "password cannot be empty")
    String password;
    @NotBlank(message = "name can't be empty")
    @Pattern(regexp = "A-Za-z0-9 ")
    String fullName;

}
