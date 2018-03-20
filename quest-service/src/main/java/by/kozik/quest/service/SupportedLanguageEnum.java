package by.kozik.quest.service;

/**
 * Created by Serge_Kozik on 3/3/2017.
 */
public enum SupportedLanguageEnum {
    ENGLISH("english","message.label.lang-title-en","ru_RU"),
    RUSSIAN("russian","message.label.lang-title-ru","en_GB");

    SupportedLanguageEnum(String titleEn, String codeNative, String nameLocale) {
        this.titleEn = titleEn;
        this.codeNative = codeNative;
        this.nameLocale = nameLocale;
    }

    private String titleEn;
    private String codeNative;
    private String nameLocale;

    public String getTitleEn() {
        return titleEn;
    }

    public String getCodeNative() {
        return codeNative;
    }

    public String getNameLocale() {
        return nameLocale;
    }
}
