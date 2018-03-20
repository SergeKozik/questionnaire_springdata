package by.kozik.quest.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge on 17.02.2017.
 */
public class UserBeanUserList {
    private int id;
    private String nick;
    private String email;
    private String role;
    private List<FormActionBean> buttons;

    public UserBeanUserList() {
        buttons = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<FormActionBean> getButtons() {
        return buttons;
    }

    public void setButtons(List<FormActionBean> buttons) {
        this.buttons = buttons;
    }
}
