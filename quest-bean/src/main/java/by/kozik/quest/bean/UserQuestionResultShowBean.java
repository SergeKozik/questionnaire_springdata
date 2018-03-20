package by.kozik.quest.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge_Kozik on 3/29/2017.
 */
public class UserQuestionResultShowBean implements Serializable {

    private String formulation;
    private List<UserAnswerResult> textAnswers;

    public UserQuestionResultShowBean() {
        textAnswers = new ArrayList<>();
    }

    public String getFormulation() {
        return formulation;
    }

    public void setFormulation(String formulation) {
        this.formulation = formulation;
    }

    public List<UserAnswerResult> getTextAnswers() {
        return textAnswers;
    }

    public void setTextAnswers(List<UserAnswerResult> textAnswers) {
        this.textAnswers = textAnswers;
    }
}
