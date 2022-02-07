package im.juniq.telegram_handler;

public class TelegramHandler {
    private final String telegram;

    public TelegramHandler(String telegram) {
        this.telegram = telegram;
    }

    public String get(int beginIndex, int endIndex) {
        return telegram.substring(beginIndex, endIndex);
    }

    public int length() {
        return telegram.length();
    }
}
