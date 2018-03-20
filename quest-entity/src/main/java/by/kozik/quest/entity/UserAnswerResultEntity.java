package by.kozik.quest.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Serge_Kozik on 3/13/2017.
 */
@Entity
@Table(name = "user_answer")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserAnswerResultEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date",nullable = false, updatable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_main_result_id",nullable = false, updatable = false, insertable = true)
    private UserMainResultEntity userMainResult;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_variant_id",nullable = false, updatable = false, insertable = true)
    private AnswerEntity userAnswer;

    public UserAnswerResultEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserMainResultEntity getUserMainResult() {
        return userMainResult;
    }

    public void setUserMainResult(UserMainResultEntity userMainResult) {
        this.userMainResult = userMainResult;
    }

    public AnswerEntity getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(AnswerEntity userAnswer) {
        this.userAnswer = userAnswer;
    }
}
