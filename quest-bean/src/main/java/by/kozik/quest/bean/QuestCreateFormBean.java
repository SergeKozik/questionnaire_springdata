package by.kozik.quest.bean;

import by.kozik.quest.bean.validation.QuestCategory;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Serge_Kozik on 2/28/2017.
 */

@QuestCategory(message = "{message.label.error.quest-category}")
public class QuestCreateFormBean implements Serializable {

    @NotBlank(message = "{message.label.error.quest-title}")
    private String title;

    private String author;

    private String description;

    private String category;

    @NotBlank(message = "{message.label.error.quest-language}")
    private String language;

    private boolean ownCategory;

    private String ownCategoryName;

    @NotBlank(message = "{message.label.error.quest-type}")
    private String type;

    private Date creationDate;

    private List<QuestionFormBean> questions;

    public QuestCreateFormBean() {
        questions = new ArrayList<>();
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isOwnCategory() {
        return ownCategory;
    }

    public void setOwnCategory(boolean ownCategory) {
        this.ownCategory = ownCategory;
    }

    public String getOwnCategoryName() {
        return ownCategoryName;
    }

    public void setOwnCategoryName(String ownCategoryName) {
        this.ownCategoryName = ownCategoryName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<QuestionFormBean> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionFormBean> questions) {
        this.questions = questions;
    }

    public String getAuthor() {
        return author;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
