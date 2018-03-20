package by.kozik.quest.bean;

import java.io.Serializable;

/**
 * Created by Serge_Kozik on 3/10/2017.
 */
public class UserAnswerResult implements Serializable {

    private int id;
    private int answerId;

    public UserAnswerResult() {
    }

    public UserAnswerResult(int answerId) {
        this.answerId = answerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

}
