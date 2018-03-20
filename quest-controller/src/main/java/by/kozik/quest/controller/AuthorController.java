package by.kozik.quest.controller;

import by.kozik.quest.bean.*;
import by.kozik.quest.exception.MissingParameterInSessionException;
import by.kozik.quest.exception.SaveBeanException;
import by.kozik.quest.service.QuestService;
import by.kozik.quest.service.QuestTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
 * Created by Serge_Kozik on 2/28/2017.
 */
@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private QuestService questService;

    @Autowired
    private MessageSource messageSource;

    private ModelAndView prepareForQuestTitleCreate(QuestCreateFormBean formBean, Locale locale) {
        if (formBean==null) {
            formBean = new QuestCreateFormBean();
            formBean.setDescription(messageSource.getMessage("message.text.quest-description",null,locale));
        }
        ModelAndView mav = new ModelAndView("quest-title-create.page","quest_new_title", formBean);
        List<String> languages = questService.returnAvailableLanguages(locale);
        List<String> categories = questService.returnAvailableCategories();
        Map<String,String> languageMap = new LinkedHashMap<String, String>();
        Map<String,String> categoryMap = new LinkedHashMap<String, String>();
        for (String item:languages) {
            languageMap.put(item,item);
        }
        for (String item:categories) {
            categoryMap.put(item,item);
        }
        List<QuestTypeEnumBean> typeBeans = new ArrayList<>();
        for (QuestTypeEnum typeEnum:QuestTypeEnum.values()) {
            typeBeans.add(new QuestTypeEnumBean(typeEnum.getNameEn(),messageSource.getMessage(typeEnum.getNameCode(),null,locale),messageSource.getMessage(typeEnum.getDescriptionCode(),null,locale)));
        }
        mav.addObject("type_beans",typeBeans);
        mav.addObject("languages",languageMap);
        mav.addObject("categories",categoryMap);
        return mav;
    }

    @RequestMapping(value = "quest-create.html")
    public ModelAndView doStartCreateQuest(Locale locale) {
        return prepareForQuestTitleCreate(null,locale);
    }

    @RequestMapping(value = "quest-title-create.html", method = RequestMethod.POST)
    public ModelAndView doCreateQuestTitle(@ModelAttribute("quest_new_title") @Valid QuestCreateFormBean questTitleBean, BindingResult bindingResult, Locale locale, HttpServletRequest request)
            throws MissingParameterInSessionException
    {
        if (bindingResult.hasErrors()) {
            return prepareForQuestTitleCreate(questTitleBean,locale);
        }
        HttpSession session = request.getSession();
        Object userObj = session.getAttribute("userProfile");
        if (userObj==null) {
            throw new MissingParameterInSessionException("User Profile was not found.");
        }
        UserProfileBean userProfileBean = (UserProfileBean)userObj;
        questTitleBean.setAuthor(userProfileBean.getUsername());
        session.setAttribute("newQuest",questTitleBean);
        switch (questTitleBean.getType()) {
            case "voting":
                return new ModelAndView("question-vote.page","form_question",new QuestionFormBean());
            case "test":
                return new ModelAndView("question-mark.page","form_question",new QuestionFormBean());
            case "questionnaire":
                return new ModelAndView("question-nomark.page","form_question",new QuestionFormBean());
            default:
                return new ModelAndView("redirect:/main.html");
        }
    }

    @RequestMapping(value = "next-question.html")
    public ModelAndView doSaveQuestionMark(@ModelAttribute("form_question") @Valid QuestionFormBean questionFormBean, BindingResult bindingResult,Locale locale, HttpServletRequest request)
            throws MissingParameterInSessionException
    {
        HttpSession session = request.getSession();
        Object questObj = session.getAttribute("newQuest");
        if (questObj==null) {
            throw new MissingParameterInSessionException("Quest object was not found.");
        }
        QuestCreateFormBean questBean = (QuestCreateFormBean)questObj;
        String typeQuest=questBean.getType();
        String resultView = "redirect:main.html";
        if (typeQuest.equals(QuestTypeEnum.TEST_MARK.getNameEn())) {
            resultView = "question-mark.page";
        }
        if (typeQuest.equals(QuestTypeEnum.QUESTIONNAIRE.getNameEn())) {
            resultView = "question-nomark.page";
        }
        if (typeQuest.equals(QuestTypeEnum.VOTING.getNameEn())) {
            resultView = "question-voting.page";
        }
        String linkRedirect = request.getParameter("typeLink");
        if ("toNew".equals(linkRedirect)) {
            return new ModelAndView(resultView,"form_question",new QuestionFormBean());
        }
        if (bindingResult.hasErrors()) {
            return new ModelAndView(resultView,"form_question",questionFormBean);
        }
        String[] variants = request.getParameterValues("answer_var");
        String[] marks = request.getParameterValues("answer_mark");
        if (variants!=null) {
            if (typeQuest.equals(QuestTypeEnum.TEST_MARK.getNameEn())) {
                if ((marks==null)||(marks.length!=variants.length)) {
                    ModelAndView mav = new ModelAndView(resultView,"form_question",questionFormBean);
                    mav.addObject("error_question_message",messageSource.getMessage("message.label.error.question-variants",null,locale));
                    return mav;
                }
                for (int ii=0;ii<variants.length; ii++) {
                    questionFormBean.getAnswers().add(new AnswerMarkBean(variants[ii].trim(),Double.parseDouble(marks[ii])));
                }
            } else {
                for (int ii=0;ii<variants.length; ii++) {
                    questionFormBean.getAnswers().add(new AnswerParentBean(variants[ii].trim()));
                }
            }
        }
        String[] userTexts = request.getParameterValues("answer_text");
        if (userTexts!=null) {
            for (String userCaption:userTexts) {
                questionFormBean.getAnswers().add(new AnswerUserTextBean(userCaption));
            }
        }
        if (questionFormBean.getAnswers().size()<1) {
            ModelAndView mav = new ModelAndView(resultView,"form_question",questionFormBean);
            mav.addObject("error_question_message",messageSource.getMessage("message.label.error.question-answers-notfound",null,locale));
            return mav;
        }
        questBean.getQuestions().add(questionFormBean);
        if (("toFinish".equals(linkRedirect))||(typeQuest.equals(QuestTypeEnum.VOTING.getNameEn()))) {
            return new ModelAndView("redirect:/author/quest-before-save.html");
        }
        return new ModelAndView(resultView,"form_question",new QuestionFormBean());
    }

    @RequestMapping(value = "quest-before-save.html")
    public ModelAndView doBeforeSaveQuest(HttpServletRequest request, Locale locale)
            throws MissingParameterInSessionException
    {
        HttpSession session = request.getSession();
        Object questObj = session.getAttribute("newQuest");
        if (questObj==null) {
            throw new MissingParameterInSessionException("Quest object was not found.");
        }
        QuestCreateFormBean questBean = (QuestCreateFormBean)questObj;
        request.setAttribute("quest_title",questBean.getTitle());
        request.setAttribute("quest_language",questBean.getLanguage());
        request.setAttribute("quest_category",questBean.getCategory());
        request.setAttribute("quest_type",questBean.getType());
        request.setAttribute("quest_description",questBean.getDescription());
        request.setAttribute("quest_questions",questBean.getQuestions().size());
        request.setAttribute("quest_author",questBean.getAuthor());
        return new ModelAndView("quest-save.page");
    }

    @RequestMapping(value = "quest-save.html")
    public ModelAndView doSaveQuest(HttpServletRequest request)
            throws MissingParameterInSessionException, SaveBeanException
    {
        HttpSession session = request.getSession();
        Object questObj = session.getAttribute("newQuest");
        if (questObj==null) {
            throw new MissingParameterInSessionException("Quest object was not found.");
        }
        QuestCreateFormBean questBean = (QuestCreateFormBean)questObj;
        questBean.setCreationDate(new Date(System.currentTimeMillis()));
        if (questService.saveQuest(questBean)) {
            return new ModelAndView("redirect:/quests.html");
        } else {
            throw new SaveBeanException("Quest entity was not saved in database.");
        }
    }

    @RequestMapping(value = "quest-save-cancel.html")
    public ModelAndView doCancelQuestCreate(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("newQuest",null);
        return new ModelAndView("redirect:main.html");
    }

}
