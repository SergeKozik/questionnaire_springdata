package by.kozik.quest.bean;

import java.io.Serializable;

/**
 * Created by Serge_Kozik on 3/29/2017.
 */
public class UserTextAnswerResultShowBean implements Serializable {

    private String formulation;

    public UserTextAnswerResultShowBean() {
    }

    public String getFormulation() {
        return formulation;
    }

    public void setFormulation(String formulation) {
        this.formulation = formulation;
    }
}
