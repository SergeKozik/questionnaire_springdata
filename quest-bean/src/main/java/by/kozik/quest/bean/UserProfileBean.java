package by.kozik.quest.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Serge_Kozik on 3/1/2017.
 */
public class UserProfileBean implements Serializable {
    private String username;
    private String roleName;
    private List<String> permissionNames;
    private String email;
    private int id;

    public UserProfileBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<String> getPermissionNames() {
        return permissionNames;
    }

    public void setPermissionNames(List<String> permissionNames) {
        this.permissionNames = permissionNames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
