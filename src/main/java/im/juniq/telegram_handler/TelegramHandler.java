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

        try {
            return telegram.substring(beginIndex - 1, endIndex);
        } catch (StringIndexOutOfBoundsException e) {
            throw new RuntimeException("전문길이와 index가 맞지 않습니다. telegram length: " + telegram.length() + ", bedinIndex: " + beginIndex + ", endIndex: " + endIndex);
        }
    }

    public int isLengthOf(int length) {
        return Integer.compare(length, telegram.length());
    }
}
