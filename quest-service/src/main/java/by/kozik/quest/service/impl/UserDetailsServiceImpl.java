package by.kozik.quest.service.impl;

import by.kozik.quest.bean.UserBeanForUserService;
import by.kozik.quest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Serge on 11.02.2017.
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserBeanForUserService user = userService.findByEmail(email);
        if (user==null) {
            throw new UsernameNotFoundException(messageSource.getMessage("message.label.error.login-fault",null, LocaleContextHolder.getLocale())+ email);
        }
        return user;
    }
}
