package by.kozik.quest.bean;

import java.io.Serializable;

/**
 * Created by Serge_Kozik on 2/28/2017.
 */
public class QuestTypeEnumBean implements Serializable {
    private String nameEn;
    private String name18n;
    private String description;

    public QuestTypeEnumBean() {
    }

    public QuestTypeEnumBean(String nameEn, String name18n, String description) {
        this.nameEn = nameEn;
        this.name18n = name18n;
        this.description = description;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getName18n() {
        return name18n;
    }

    public void setName18n(String name18n) {
        this.name18n = name18n;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
