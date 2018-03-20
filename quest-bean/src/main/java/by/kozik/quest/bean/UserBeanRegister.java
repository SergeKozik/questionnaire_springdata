package by.kozik.quest.bean;

import by.kozik.quest.bean.validation.PasswordMatches;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by Serge on 10.02.2017.
 */

@PasswordMatches(message = "{message.label.error.password-compare}")
public class UserBeanRegister implements Serializable {

    @Pattern(regexp = "(^[A-Z]\\w{4,}$)", message = "{message.label.error.nick-nonvalid}")
    private String login;

    @Pattern(regexp = "(?=^.{6,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*", message = "{message.label.error.password-nonvalid}")
    private String password;

    @Email(message = "{message.label.error.email-nonvalid}")
    private String email;

    @NotNull(message = "{message.label.error.password-compare}")
    private String passwordConfirm;

    @NotNull(message = "{message.label.error.role-missing}")
    private String roleName;

    public UserBeanRegister() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
