package by.kozik.quest.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Serge_Kozik on 3/9/2017.
 */
public class UserQuestResultBean implements Serializable {

    private int id;
    private Date date;
    private int questId;
    private int userId;
    private List<UserAnswerResult> answers;

    public UserQuestResultBean() {
        answers = new ArrayList<>();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuestId() {
        return questId;
    }

    public void setQuestId(int questId) {
        this.questId = questId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<UserAnswerResult> getAnswers() {
        return answers;
    }

    public void setAnswers(List<UserAnswerResult> answers) {
        this.answers = answers;
    }
}
