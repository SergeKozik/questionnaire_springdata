package by.kozik.quest.bean;

import java.io.Serializable;

/**
 * Created by Serge on 16.01.2017.
 */
public class FormActionBean implements Serializable {
    private String buttonLink;
    private String buttonCaption;

    public FormActionBean() {
    }

    public FormActionBean(String href, String buttonCaption) {
        this.buttonLink = href;
        this.buttonCaption = buttonCaption;
    }

    public String getButtonLink() {
        return buttonLink;
    }

    public void setButtonLink(String buttonLink) {
        this.buttonLink = buttonLink;
    }

    public String getButtonCaption() {
        return buttonCaption;
    }

    public void setButtonCaption(String buttonCaption) {
        this.buttonCaption = buttonCaption;
    }
}
