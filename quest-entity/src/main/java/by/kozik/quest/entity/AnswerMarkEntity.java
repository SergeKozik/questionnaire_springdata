package by.kozik.quest.entity;

import javax.persistence.*;

/**
 * Created by Serge on 27.02.2017.
 */
@Entity
@Table(name = "answer_variant_mark")
@PrimaryKeyJoinColumn(name = "answer_variant_id")
public class AnswerMarkEntity extends AnswerEntity {


    @Column(name = "mark")
    private double mark;

    public AnswerMarkEntity() {
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}
