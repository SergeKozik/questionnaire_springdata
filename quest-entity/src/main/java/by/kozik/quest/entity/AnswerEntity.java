package by.kozik.quest.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Serge on 27.02.2017.
 */
@Entity
@Table(name = "answer_variant")
@Inheritance(strategy = InheritanceType.JOINED)
public class AnswerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "formulation")
    private String formulation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_item_id",nullable = false, updatable = false, insertable = true)
    private QuestionEntity question;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "userAnswer")
    private List<UserAnswerResultEntity> userAnswers;

    public AnswerEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFormulation() {
        return formulation;
    }

    public void setFormulation(String formulation) {
        this.formulation = formulation;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerEntity that = (AnswerEntity) o;

        if (id != that.id) return false;
        if (formulation != null ? !formulation.equals(that.formulation) : that.formulation != null) return false;
        return question != null ? question.equals(that.question) : that.question == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (formulation != null ? formulation.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        return result;
    }
}
