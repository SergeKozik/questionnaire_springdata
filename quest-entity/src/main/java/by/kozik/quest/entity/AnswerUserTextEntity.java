package by.kozik.quest.entity;

import javax.persistence.*;

/**
 * Created by Serge on 27.02.2017.
 */
@Entity
@Table(name = "answer_variant_text")
@PrimaryKeyJoinColumn(name = "answer_variant_id")
public class AnswerUserTextEntity extends AnswerEntity {

    public AnswerUserTextEntity() {
    }


}
