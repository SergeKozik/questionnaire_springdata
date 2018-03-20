package by.kozik.quest.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Serge on 27.02.2017.
 */
@Entity
@Table(name = "quest_list")
public class QuestEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "field")
    private String category;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date",nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "author")
    private String author;

    @Column(name = "type_result")
    private String type;

    @Column(name = "language")
    private String language;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "quest")
    private List<QuestionEntity> questions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quest")
    private List<UserMainResultEntity> userResults;

    public QuestEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
    }

    public List<UserMainResultEntity> getUserResults() {
        return userResults;
    }

    public void setUserResults(List<UserMainResultEntity> userResults) {
        this.userResults = userResults;
    }
}
