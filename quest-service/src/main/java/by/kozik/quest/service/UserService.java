package by.kozik.quest.service;

import by.kozik.quest.bean.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * Created by Serge on 11.02.2017.
 */
public interface UserService {

    public UserBeanForUserService findByEmail(String email);
    public UserBeanRegister register(UserBeanRegister user);
    public List<UserBeanUserList> returnAllEnabled(String editLink, String editCaption, String deleteLink, String deleteCaption, Collection<String> currentPermissions);
    public List<UserBeanUserList> returnAllDisabled(String restoreLink, String restoreCaption, Collection<String> currentPermissions);
    public void disableUserById(int id, Collection<String> currentPermissions);
    public void enableUserById(int id, Collection<String> currentPermissions);
    public Collection<String> AuthoritiesToPermissions(Collection<SimpleGrantedAuthority> authorities);
    public UserProfileBean returnUserProfileBean(String userEmail);

}
