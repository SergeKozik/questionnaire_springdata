package by.kozik.quest.bean;

import java.io.Serializable;

/**
 * Created by Serge_Kozik on 3/13/2017.
 */
public class AnswerDoubleResultBean implements Serializable {
    private String formulation;
    private double doubleValue;
    private String stringValue;

    public AnswerDoubleResultBean() {
    }

    public AnswerDoubleResultBean(String formulation, double doubleValue, String stringValue) {
        this.formulation = formulation;
        this.doubleValue = doubleValue;
        this.stringValue = stringValue;
    }

    public String getFormulation() {
        return formulation;
    }

    public void setFormulation(String formulation) {
        this.formulation = formulation;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
}
