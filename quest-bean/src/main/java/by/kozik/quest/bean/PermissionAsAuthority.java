package by.kozik.quest.bean;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Serge on 11.02.2017.
 */
public class PermissionAsAuthority implements GrantedAuthority {

    private String permissionName;

    public PermissionAsAuthority() {
    }

    public PermissionAsAuthority(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getAuthority() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
