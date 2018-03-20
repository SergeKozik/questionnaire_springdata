package by.kozik.quest.bean;

/**
 * Created by Serge_Kozik on 3/1/2017.
 */
public class AnswerMarkBean extends AnswerParentBean {
    private double mark;

    public AnswerMarkBean(String formulation, double mark) {
        super(formulation);
        this.mark = mark;
    }

    public AnswerMarkBean() {
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}
