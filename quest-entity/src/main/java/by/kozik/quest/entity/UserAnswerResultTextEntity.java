package by.kozik.quest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by Serge on 17.03.2017.
 */
@Entity
@Table(name = "user_answer_text")
@PrimaryKeyJoinColumn(name = "user_answer_id")
public class UserAnswerResultTextEntity extends UserAnswerResultEntity {

    @Column
    private String text;

    public UserAnswerResultTextEntity() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
