package by.kozik.quest.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Serge on 27.02.2017.
 */
@Entity
@Table(name = "question_item")
public class QuestionEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "quest_list_id", referencedColumnName = "id", nullable = false)
    private QuestEntity quest;

    @Column(name = "formulation")
    private String formulation;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "question")
    List<AnswerEntity> answers;

    public QuestionEntity() {
    }

    public int getId() {
        return id;
    }

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuestEntity getQuest() {
        return quest;
    }

    public void setQuest(QuestEntity quest) {
        this.quest = quest;
    }

    public String getFormulation() {
        return formulation;
    }

    public void setFormulation(String formulation) {
        this.formulation = formulation;
    }

}
