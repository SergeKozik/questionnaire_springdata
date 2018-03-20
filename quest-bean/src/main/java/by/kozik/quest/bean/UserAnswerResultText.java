package by.kozik.quest.bean;

/**
 * Created by Serge on 17.03.2017.
 */
public class UserAnswerResultText extends UserAnswerResult {

    private String userText;

    public UserAnswerResultText() {
        super();
    }

    public UserAnswerResultText(int answerId, String userText) {
        super(answerId);
        this.userText = userText;
    }

    public String getUserText() {
        return userText;
    }

    public void setUserText(String userText) {
        this.userText = userText;
    }
}
