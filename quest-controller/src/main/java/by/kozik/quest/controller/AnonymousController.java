package by.kozik.quest.controller;

import by.kozik.quest.bean.*;
import by.kozik.quest.service.QuestService;
import by.kozik.quest.service.RoleService;
import by.kozik.quest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by Serge on 15.02.2017.
 */
@Controller
public class AnonymousController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private QuestService questService;

    @RequestMapping("/main.html")
    public String doMainPage() {
        return "main.page";
    }

    @RequestMapping("/login.html")
    public ModelAndView goLogin() {
        return new ModelAndView("login.page", "login_user", new UserBeanLogin());
    }

    @RequestMapping("/language.html")
    public String doTranslate(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object pageName = session.getAttribute("current_page");
        if (pageName!=null) {
            return (String)pageName;
        }
        return "main.page";
    }

    @RequestMapping(value = "/login-user.html", method = RequestMethod.POST)
    public ModelAndView doLoginUser(@ModelAttribute("login_user") @Valid UserBeanLogin user, BindingResult bindingResult, Locale locale, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("login.page", "user_login", user);
            mav.addObject("error_login_message", messageSource.getMessage("message.label.error.login-js-validation", null, locale));
            return mav;
        }
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        UserProfileBean userProfile = userService.returnUserProfileBean(user.getEmail());
        request.getSession().setAttribute("userProfile",userProfile);
        return new ModelAndView("redirect:/main.html");
    }

    @RequestMapping("/register.html")
    public ModelAndView goRegister() {
        ModelAndView mav = new ModelAndView("register.page","register_user",new UserBeanRegister());
        Map<String,String> roleMap = new LinkedHashMap<String, String>();
        List<String> rolesFromDB = roleService.findAllRoleNames();
        for (String item:rolesFromDB) {
            roleMap.put(item,item);
        }
        mav.addObject("role_names",roleMap);
        return mav;
    }

    @RequestMapping(value = "/register-user.html", method = RequestMethod.POST)
    public ModelAndView doRegisterUser(@ModelAttribute("register_user") @Valid UserBeanRegister user, BindingResult bindingResult, Locale locale,HttpServletRequest request) {
        Map<String,String> roleMap = new LinkedHashMap<String, String>();
        if (bindingResult.hasErrors()) {
            List<String> rolesFromDB = roleService.findAllRoleNames();
            for (String item:rolesFromDB) {
                roleMap.put(item,item);
            }
            ModelAndView mav = new ModelAndView("register.page","register_user",user);
            mav.addObject("role_names",roleMap);
            return mav;
        }
        UserBeanRegister user2 = userService.register(user);
        if (user2==null) {
            ModelAndView mav = new ModelAndView("register.page","register_user",user);
            List<String> rolesFromDB = roleService.findAllRoleNames();
            for (String item:rolesFromDB) {
                roleMap.put(item,item);
            }
            mav.addObject("role_names",roleMap);
            mav.addObject("error_register_message",messageSource.getMessage("message.label.error.register-fault",null,locale));
            return mav;
        }
        UserProfileBean userProfile = userService.returnUserProfileBean(user2.getEmail());
        request.getSession().setAttribute("userProfile",userProfile);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        return new ModelAndView("redirect:/main.html");
    }

    @RequestMapping(value = "/quests.html")
    public ModelAndView doShowQuests(HttpServletRequest request, Locale locale) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username="";
        Collection<String> permissions = new HashSet<>();
        if (authentication!=null) {
            Object userObj = request.getSession().getAttribute("userProfile");
            if (userObj!=null) {
                UserProfileBean userProfileBean = (UserProfileBean)userObj;
                username = userProfileBean.getUsername();
            }
            Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)authentication.getAuthorities();
            permissions = userService.AuthoritiesToPermissions(authorities);
        }
        ModelAndView mav = new ModelAndView("quests.page");
        FormActionBean startButton = new FormActionBean(request.getContextPath()+"/user/quest-start.html",messageSource.getMessage("message.button.quest-start",null,locale));
        FormActionBean deleteButton = new FormActionBean(request.getContextPath()+"/author/quest-delete.html",messageSource.getMessage("message.button.delete",null,locale));
        FormActionBean viewResultButton = new FormActionBean(request.getContextPath()+"/user/view-result.html",messageSource.getMessage("message.button.quest-statistics",null,locale));
        List<QuestShowBean> beans = questService.returnQuestShowBeans(permissions,null,null,null,username,startButton,deleteButton,viewResultButton,locale);
        HttpSession session = request.getSession();
        session.setAttribute("quests",beans);
        List<String> languages = questService.returnAvailableLanguages(locale);
        List<String> categories = questService.returnAvailableCategories();
        List<QuestTypeEnumBean> typeBeans = questService.returnAvailableTypes(locale);
        session.setAttribute("types",typeBeans);
        session.setAttribute("languages",languages);
        session.setAttribute("categories",categories);
        return mav;
    }
}
