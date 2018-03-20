package by.kozik.quest.controller;

import by.kozik.quest.bean.RoleBeanFromForm;
import by.kozik.quest.bean.UserBeanUserList;
import by.kozik.quest.service.PermissionService;
import by.kozik.quest.service.RoleService;
import by.kozik.quest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by Serge on 15.02.2017.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping("roles.html")
    public ModelAndView doRolePage() {
        ModelAndView mav = new ModelAndView("roles.page","roles_form",new RoleBeanFromForm());
        Map<String,String> roleMap = new LinkedHashMap<String, String>();
        List<String> rolesFromDB = roleService.findAllRoleNames();
        for (String item:rolesFromDB) {
            roleMap.put(item,item);
        }
        mav.addObject("role_names",roleMap);
        mav.addObject("perm_names", new ArrayList<String>());
        return mav;
    }

    @RequestMapping("role-edit.html")
    public ModelAndView doRoleEdit(@ModelAttribute("roles_form") @Valid RoleBeanFromForm roleBeanFromForm, BindingResult bindingResult) {
        if ((!bindingResult.hasErrors())&&(roleService.saveRole(roleBeanFromForm))) {
            return new ModelAndView("redirect:/admin/roles.html");
        }
        ModelAndView mav = new ModelAndView("roles.page","roles_form",roleBeanFromForm);
        List<String> permNames = permissionService.returnAllNames();
        mav.addObject("perm_names",permNames);
        return mav;
    }

    @RequestMapping("users.html")
    public String doUserList(HttpServletRequest request, Locale locale) {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Collection<String> permissions = userService.AuthoritiesToPermissions(authorities);
        List<UserBeanUserList> userListEnabled = userService.returnAllEnabled(request.getContextPath()+"/admin/user-edit.html",messageSource.getMessage("message.button.edit",null,locale),request.getContextPath()+"/admin/user-delete.html",messageSource.getMessage("message.button.delete",null,locale),permissions);
        List<UserBeanUserList> userListDisabled = userService.returnAllDisabled(request.getContextPath()+"/admin/user-restore.html",messageSource.getMessage("message.button.restore",null,locale),permissions);
        HttpSession session = request.getSession();
        session.setAttribute("user_list",userListEnabled);
        session.setAttribute("user_list_bin",userListDisabled);
        return "users.page";
    }

    @RequestMapping("role-delete.html")
    public ModelAndView doRoleDelete(HttpServletRequest request) {
        String roleName = request.getParameter("roleName");
        if ((roleName!=null)&&(!roleName.isEmpty())) {
            roleService.deleteRole(roleName);
        }
        return new ModelAndView("redirect:/admin/roles.html");
    }

    @RequestMapping("user-delete.html")
    public ModelAndView doUserDelete(HttpServletRequest request) {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Collection<String> permissions = userService.AuthoritiesToPermissions(authorities);
        String userId = request.getParameter("user_id");
        if ((userId!=null)&&(!userId.isEmpty())) {
            userService.disableUserById(Integer.parseInt(userId),permissions);
        }
        return new ModelAndView("redirect:/admin/users.html");
    }
}
