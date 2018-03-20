package by.kozik.quest.bean;

import by.kozik.quest.bean.validation.FlagCreateRoleMatches;

import java.io.Serializable;

/**
 * Created by Serge on 13.02.2017.
 */
@FlagCreateRoleMatches(message = "{message.label.error.role-notfound}")
public class RoleBeanFromForm implements Serializable {

    private String flagCreate;

    private String roleName;

    private String ownRoleName;

    private String[] permissionNames;

    public RoleBeanFromForm() {

    }

    public String getFlagCreate() {
        return flagCreate;
    }

    public void setFlagCreate(String flagCreate) {
        this.flagCreate = flagCreate;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getOwnRoleName() {
        return ownRoleName;
    }

    public void setOwnRoleName(String ownRoleName) {
        this.ownRoleName = ownRoleName;
    }

    public String[] getPermissionNames() {
        return permissionNames;
    }

    public void setPermissionNames(String[] permissionNames) {
        this.permissionNames = permissionNames;
    }
}
