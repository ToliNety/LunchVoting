package org.tolinety.springrest.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.tolinety.springrest.model.User;

/**
 * Created by tolin on 25.06.2017.
 */
@Data
@AllArgsConstructor
public class UserTo {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Length(min = 5)
    private String password;

    public static User getFromTo(UserTo userTo) {
        return new User(userTo.getEmail(), userTo.getPassword());
    }
}
