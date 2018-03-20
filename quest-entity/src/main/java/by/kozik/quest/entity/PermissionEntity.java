package by.kozik.quest.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Serge on 08.02.2017.
 */
@Entity
@Table(name = "permission")
public class PermissionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "permission_to_role",
            joinColumns = {
                    @JoinColumn(name = "permission_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")
            })
    //@ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
    private List<RoleEntity> roles;

    @Column(name = "perm_name")
    private String permissionName;

    @Column(name = "perm_desc")
    private String description;

    public PermissionEntity() {
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermissionEntity that = (PermissionEntity) o;

        if (getId() != that.getId()) return false;
        if (!getPermissionName().equals(that.getPermissionName())) return false;
        return getDescription().equals(that.getDescription());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getPermissionName().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }
}
