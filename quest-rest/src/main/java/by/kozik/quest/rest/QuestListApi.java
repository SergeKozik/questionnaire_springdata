package by.kozik.quest.rest;

import by.kozik.quest.bean.FormActionBean;
import by.kozik.quest.bean.QuestShowBean;
import by.kozik.quest.bean.UserProfileBean;
import by.kozik.quest.service.QuestService;
import by.kozik.quest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Serge_Kozik on 3/6/2017.
 */

@RestController
@RequestMapping(value = "/rest/quests", produces = "application/json")
public class QuestListApi {

    @Autowired
    private QuestService questService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<QuestShowBean>> getQuests(@RequestParam("quest_lang") List<String> languages,
                                                         @RequestParam("quest_type") List<String> types,
                                                         @RequestParam("quest_categ") List<String> categories,
                                                         HttpServletRequest request,
                                                         Locale locale) {
        Object questListObj = request.getSession().getAttribute("quests");
        if (questListObj==null) {
            return new ResponseEntity<List<QuestShowBean>>(new ArrayList<QuestShowBean>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        List<QuestShowBean> questList = (List<QuestShowBean>)questListObj;
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
        FormActionBean startButton = new FormActionBean(request.getContextPath()+"/user/quest-start.html",messageSource.getMessage("message.button.quest-start",null,locale));
        FormActionBean deleteButton = new FormActionBean(request.getContextPath()+"/author/quest-delete.html",messageSource.getMessage("message.button.delete",null,locale));
        FormActionBean viewResultButton = new FormActionBean(request.getContextPath()+"/user/view-result.html",messageSource.getMessage("message.button.quest-statistics",null,locale));
        List<QuestShowBean> beans = questService.returnQuestShowBeans(permissions,languages,types,categories,username,startButton,deleteButton,viewResultButton,locale);
        return new ResponseEntity<List<QuestShowBean>>(beans, HttpStatus.CREATED);
    }

}
