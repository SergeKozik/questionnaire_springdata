package by.kozik.quest.bean;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge_Kozik on 3/1/2017.
 */
public class QuestionFormBean implements Serializable {

    private int id;

    @NotBlank(message = "{message.label.error.question-title}")
    private String formulation;

    private List<AnswerParentBean> answers;

    public QuestionFormBean() {
        answers = new ArrayList<>();
    }

    public String getFormulation() {
        return formulation;
    }

    public void setFormulation(String formulation) {
        this.formulation = formulation;
    }

    public List<AnswerParentBean> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerParentBean> answers) {
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
