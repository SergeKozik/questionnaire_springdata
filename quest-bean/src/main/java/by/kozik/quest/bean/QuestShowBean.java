package by.kozik.quest.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge_Kozik on 3/2/2017.
 */
public class QuestShowBean implements Serializable {

    private int id;
    private String title;
    private String description;
    private String authorName;
    private String author;
    private String categoryName;
    private String category;
    private String languageName;
    private String language;
    private String typeResultName;
    private String typeResultNative;
    private String dateName;
    private String creationDate;
    private List<FormActionBean> buttons;
    private String numQuestionsName;
    private int numQuestions;
    private String passedName;
    private int passed;


    public QuestShowBean() {
        buttons = new ArrayList<>();
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getTypeResultNative() {
        return typeResultNative;
    }

    public void setTypeResultNative(String typeResultNative) {
        this.typeResultNative = typeResultNative;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public List<FormActionBean> getButtons() {
        return buttons;
    }

    public void setButtons(List<FormActionBean> buttons) {
        this.buttons = buttons;
    }

    public int getNumQuestions() {
        return numQuestions;
    }

    public void setNumQuestions(int numQuestions) {
        this.numQuestions = numQuestions;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getTypeResultName() {
        return typeResultName;
    }

    public void setTypeResultName(String typeResultName) {
        this.typeResultName = typeResultName;
    }

    public String getDateName() {
        return dateName;
    }

    public void setDateName(String dateName) {
        this.dateName = dateName;
    }

    public String getNumQuestionsName() {
        return numQuestionsName;
    }

    public void setNumQuestionsName(String numQuestionsName) {
        this.numQuestionsName = numQuestionsName;
    }

    public String getPassedName() {
        return passedName;
    }

    public void setPassedName(String passedName) {
        this.passedName = passedName;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
