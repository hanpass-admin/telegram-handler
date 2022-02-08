package im.juniq.telegram_handler;

public class TelegramHandler {
    private String telegram;

    public TelegramHandler(String telegram) {
        this.telegram = telegram;
    }

    public String field(int beginIndex, int endIndex) {
        if (beginIndex < 1) {
            throw new RuntimeException("beginIndex는 0보다 커야합니다. telegram length: " + telegram.length() + ", bedinIndex: " + beginIndex + ", endIndex: " + endIndex);
        }

        if (endIndex > telegram.length()) {
            throw new RuntimeException("endIndex가 전문길이보다 큽니다. telegram length: " + telegram.length() + ", bedinIndex: " + beginIndex + ", endIndex: " + endIndex);
        }

        try {
            return telegram.substring(beginIndex - 1, endIndex);
        } catch (StringIndexOutOfBoundsException e) {
            throw new RuntimeException("전문길이와 index가 맞지 않습니다. telegram length: " + telegram.length() + ", bedinIndex: " + beginIndex + ", endIndex: " + endIndex);
        }
    }

    public int isLengthOf(int length) {
        return Integer.compare(length, telegram.length());
    }

    public void changeField(int beginIndex, int endIndex, String newField) {
        if (endIndex - beginIndex != newField.length() - 1) {
            throw new RuntimeException("새로운 필드의 길이는 이전 필드의 길이와 같아야 합니다.");
        }
        StringBuilder builder = new StringBuilder(telegram);
        builder.delete(beginIndex - 1, endIndex);
        builder.insert(beginIndex - 1, newField);
        telegram = builder.toString();
    }
}
