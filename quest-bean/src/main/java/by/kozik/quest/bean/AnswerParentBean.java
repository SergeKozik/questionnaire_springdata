package by.kozik.quest.bean;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by Serge_Kozik on 3/1/2017.
 */
public class AnswerParentBean implements Serializable {

    private int id;

    @NotBlank(message = "{message.label.error.question.variant-empty}")
    private String formulation;

    public AnswerParentBean(String formulation) {
        this.formulation = formulation;
    }

    public AnswerParentBean() {
    }

    public String getFormulation() {
        return formulation;
    }

    public void setFormulation(String formulation) {
        this.formulation = formulation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
