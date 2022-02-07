package im.juniq.telegram_handler;

public class TelegramHandler {
    private final String telegram;

    public TelegramHandler(String telegram) {
        this.telegram = telegram;
    }

    public String field(int beginIndex, int endIndex) {
        return telegram.substring(beginIndex - 1, endIndex);
    }

    public int isLengthOf(int length) {
        return Integer.compare(length, telegram.length());
    }
}
