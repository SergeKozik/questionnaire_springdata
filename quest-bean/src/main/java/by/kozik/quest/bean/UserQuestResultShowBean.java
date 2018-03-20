package by.kozik.quest.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge_Kozik on 3/29/2017.
 */
public class UserQuestResultShowBean implements Serializable {

    private int id;
    private String userName;
    private String questTitle;
    private String date;
    private List<UserQuestionResultShowBean> questions;

    public UserQuestResultShowBean() {
        questions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQuestTitle() {
        return questTitle;
    }

    public void setQuestTitle(String questTitle) {
        this.questTitle = questTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<UserQuestionResultShowBean> getQuestions() {
        return questions;
    }

    public void setQuestions(List<UserQuestionResultShowBean> questions) {
        this.questions = questions;
    }
}
