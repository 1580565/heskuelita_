package com.capgemini.heskuelita.core.beans;
import java.time.LocalDate;
import lombok.*;

@Data
@NoArgsConstructor
public class User {

    private String userName;
    private String password;
    private String email;
    private LocalDate created;
    private LocalDate updated;
}
