package by.kozik.quest.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serge_Kozik on 2/28/2017.
 */
public enum QuestTypeEnum {
    VOTING("voting","message.label.quest-type-voting", "message.text.quest-voting-description"),
    TEST_MARK("test","message.label.quest-type-test","message.text.quest-test-description"),
    QUESTIONNAIRE("questionnaire","message.label.quest-type-questionnaire","message.text.quest-test-questionnaire");

    private static Map<String,QuestTypeEnum> map;

    private String nameEn;
    private String nameCode;
    private String descriptionCode;

    static {
        map = new HashMap<>();
        for (QuestTypeEnum item:QuestTypeEnum.values()) {
            map.put(item.getNameEn(),item);
        }
    }

    QuestTypeEnum(String nameEn, String nameCode, String descriptionCode) {
        this.nameEn = nameEn;
        this.nameCode = nameCode;
        this.descriptionCode = descriptionCode;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameCode() {
        return nameCode;
    }

    public String getDescriptionCode() {
        return descriptionCode;
    }

    public static QuestTypeEnum getTypeByName(String testName) {
        return map.get(testName);
    }
}
