package by.kozik.quest.controller;

import by.kozik.quest.bean.*;
import by.kozik.quest.exception.BeanCreateException;
import by.kozik.quest.exception.MissingParameterInSessionException;
import by.kozik.quest.exception.SaveBeanException;
import by.kozik.quest.exception.WrongParameterException;
import by.kozik.quest.service.QuestResultService;
import by.kozik.quest.service.QuestService;
import by.kozik.quest.service.QuestTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private QuestService questService;

    @Autowired
    private QuestResultService questResultService;

    @Autowired
    private MessageSource messageSource;

    private ModelAndView prepareQuestionPage(HttpServletRequest request) throws MissingParameterInSessionException {
        HttpSession session = request.getSession();
        Object questObj = session.getAttribute("startQuest");
        Object iteratorObj = session.getAttribute("questionsIterator");
        Object resultsObj = session.getAttribute("currentResult");
        if ((questObj==null)||(iteratorObj==null)||(resultsObj==null)) {
            throw new MissingParameterInSessionException("Session with current quest has been expired.");
        }
        QuestShowBean questShowBean = (QuestShowBean)questObj;
        session.setAttribute("quest_id",questShowBean.getId());
        Iterator<QuestionFormBean> questionFormBeanIterator = (Iterator<QuestionFormBean>) iteratorObj;
        QuestionFormBean questionBean = questionFormBeanIterator.next();
        ModelAndView mav = new ModelAndView();
        if (questionBean!=null) {
            mav.setViewName("ask-question.page");
            session.setAttribute("question_id",questionBean.getId());
            session.setAttribute("question_formulation",questionBean.getFormulation());
            List<AnswerParentBean> choiceAnswers = new ArrayList<>();
            List<AnswerParentBean> userAnswers = new ArrayList<>();
            for (AnswerParentBean answer:questionBean.getAnswers()) {
                if (answer.getClass()== AnswerUserTextBean.class) {
                    userAnswers.add(answer);
                } else {
                    choiceAnswers.add(answer);
                }
            }
            session.setAttribute("question_variants",choiceAnswers);
            session.setAttribute("question_variants_user",userAnswers);
            return mav;
        } else {
            session.setAttribute("question_id",null);
            session.setAttribute("quest_id",null);
            session.setAttribute("question_formulation",null);
            session.setAttribute("question_variants",null);
            session.setAttribute("question_variants_user",null);
            return new ModelAndView("quest-finish.html");
        }
    }

    private boolean validateQuestionPage(HttpServletRequest request, Locale locale) throws MissingParameterInSessionException,WrongParameterException {
        String questionIdString = request.getParameter("question_id");
        String questIdString = request.getParameter("quest_id");
        if (questionIdString==null) {
            throw new MissingParameterInSessionException("question id was not found.");
        }
        if (questIdString==null) {
            throw new MissingParameterInSessionException("quest id was not found.");
        }
        int questionId = Integer.parseInt(questionIdString);
        int questId = Integer.parseInt(questIdString);
        HttpSession session = request.getSession();
        Object questObj = session.getAttribute("startQuest");
        if (questObj==null) {
            throw new MissingParameterInSessionException("Session with current quest has been expired.");
        }
        QuestShowBean quest = (QuestShowBean) questObj;
        int questSessionId = quest.getId();
        if (questId!=questSessionId) {
            throw new MissingParameterInSessionException("Session with current quest has been expired.");
        }
        String[] textAnswers = request.getParameterValues("current_answer_text");
        String[] userAnswers = request.getParameterValues("user_answer");
        String[] choiceAnswers = request.getParameterValues("current_answer");
        if ((textAnswers==null)&&(choiceAnswers==null)) {
            request.setAttribute("error_question_message",messageSource.getMessage("message.label.error.question-answers-notfound",null,locale));
            return false;
        }
        if ((textAnswers!=null)&&((userAnswers==null)||(textAnswers.length>userAnswers.length))) {
            throw new WrongParameterException("User did not answered for all checked answer cases.");
        }
        return true;
    }

    @RequestMapping("quest-start.html")
    public ModelAndView doStartQuest(@RequestParam(value = "quest_id", required = false) Integer questId,
                                     HttpServletRequest request)
            throws BeanCreateException, MissingParameterInSessionException, WrongParameterException
    {
        HttpSession session = request.getSession();
        if (questId==null) {
            return new ModelAndView("redirect:/main.html");
        }
        QuestShowBean questShowBean = questService.returnQuestById(questId);
        if (questShowBean==null) {
            throw new BeanCreateException("Quest bean was not created for id="+questId);
        }
        Object userObj = session.getAttribute("userProfile");
        if (userObj==null) {
            throw new MissingParameterInSessionException("User Profile was not found.");
        }
        Object questObj = session.getAttribute("startQuest");
        if (questObj!=null) {
            throw new WrongParameterException("Do no start new questionnaire when previous is not finished.");
        }
        UserProfileBean userProfileBean = (UserProfileBean)userObj;
        ModelAndView mav = new ModelAndView("quest-before-start.page");
        session.setAttribute("startQuest",questShowBean);
        List<QuestionFormBean> questionFormBeanList = questService.returnQuestionsFromQuest(questShowBean.getId());
        session.setAttribute("questionsIterator",questionFormBeanList.iterator());
        UserQuestResultBean userQuestResultBean = new UserQuestResultBean();
        userQuestResultBean.setQuestId(questShowBean.getId());
        userQuestResultBean.setUserId(userProfileBean.getId());
        session.setAttribute("currentResult", userQuestResultBean);
        mav.addObject("quest_title",questShowBean.getTitle());
        mav.addObject("quest_description",questShowBean.getDescription());
        return mav;
    }

    @RequestMapping("quest-run.html")
    public ModelAndView doRunQuest(HttpServletRequest request) throws MissingParameterInSessionException {
        return prepareQuestionPage(request);
    }

    @RequestMapping("quest-next-question.html")
    public ModelAndView doNextQuestion(HttpServletRequest request, Locale locale) throws MissingParameterInSessionException, WrongParameterException {

        if (!validateQuestionPage(request,locale)) {
            return new ModelAndView("ask-question.page");
        }
        List<UserAnswerResult> currentAnswers = new ArrayList<>();
        String[] choiceAnswers = request.getParameterValues("current_answer");
        if (choiceAnswers!=null) {
            for (String chosenAnswerId:choiceAnswers) {
                currentAnswers.add(new UserAnswerResult(Integer.parseInt(chosenAnswerId)));
            }
        }
        String[] textAnswers = request.getParameterValues("current_answer_text");
        String[] userAnswers = request.getParameterValues("user_answer");
        if (textAnswers!=null) {
            for (int ii=0;ii<textAnswers.length; ii++) {
                currentAnswers.add(new UserAnswerResultText(Integer.parseInt(textAnswers[ii]),userAnswers[ii]));
            }
        }
        HttpSession session = request.getSession();
        Object resultsObj = session.getAttribute("currentResult");
        UserQuestResultBean userQuestResultBean = (UserQuestResultBean) resultsObj;
        userQuestResultBean.getAnswers().addAll(currentAnswers);
        return prepareQuestionPage(request);
    }

    @RequestMapping("quest-finish.html")
    public ModelAndView doQuestFinish(HttpServletRequest request) throws MissingParameterInSessionException, SaveBeanException, WrongParameterException {
        HttpSession session = request.getSession();
        Object questObj = session.getAttribute("startQuest");
        Object resultsObj = session.getAttribute("currentResult");
        if ((questObj==null)||(resultsObj==null)) {
            throw new MissingParameterInSessionException("Session with current quest has been expired.");
        }
        ModelAndView mav = new ModelAndView();
        QuestShowBean questShowBean = (QuestShowBean)questObj;
        UserQuestResultBean userQuestResultBean = (UserQuestResultBean)resultsObj;
        if (userQuestResultBean.getQuestId()!=questShowBean.getId()) {
            throw new WrongParameterException("Current results do not match started quest.");
        }
        UserQuestResultBean savedResult = questResultService.saveMainResult(userQuestResultBean);
        if (savedResult==null) {
            throw new SaveBeanException("Error while saving results to database.");
        }
        mav.addObject("quest_title",questShowBean.getTitle());
        if (QuestTypeEnum.VOTING.getNameEn().equals(questShowBean.getTypeResultNative())) {
            List<AnswerDoubleResultBean> voteResults = questResultService.returnProcentChartVoteQuest(questShowBean.getId());
            mav.addObject("voteResultBeans",voteResults);
            mav.setViewName("quest-result-voting.page");
        }
        if (QuestTypeEnum.QUESTIONNAIRE.getNameEn().equals(questShowBean.getTypeResultNative())) {
            mav.setViewName("quest-result-questionnaire.page");
            mav.addObject("quest_num_questions",questShowBean.getNumQuestions());
        }
        if (QuestTypeEnum.TEST_MARK.getNameEn().equals(questShowBean.getTypeResultNative())) {
            mav.setViewName("quest-result-test.page");
            mav.addObject("quest_num_questions",questShowBean.getNumQuestions());
            mav.addObject("quest_score", questResultService.calculateUserMark(savedResult.getId()));
            mav.addObject("quest_total",questResultService.calculateTotalMark(questShowBean.getId()));
        }
        session.setAttribute("startQuest",null);
        session.setAttribute("questionsIterator",null);
        session.setAttribute("currentResult", null);
        return mav;
    }

    @RequestMapping("view-result.html")
    public ModelAndView doQuestResult(HttpServletRequest request) throws MissingParameterInSessionException {
        HttpSession session = request.getSession();
        String questIdString = request.getParameter("quest_id");
        if (questIdString==null) {
            throw new MissingParameterInSessionException("Quest id is not defined.");
        }
        int questId = Integer.parseInt(questIdString);
        Object userObj = request.getSession().getAttribute("userProfile");
        if (userObj==null) {
            throw new MissingParameterInSessionException("User is not defined in session.");
        }
        UserProfileBean userProfileBean = (UserProfileBean)userObj;
        int userId=userProfileBean.getId();
        List<UserQuestResultShowBean> resultList = questResultService.showAllQuestsResults(userId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("users_results",resultList);
        mav.setViewName("quest-statistics-user.page");
        return mav;
    }
}
