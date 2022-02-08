package im.juniq.telegram_handler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TelegramHandler {
    private final byte[] telegram;
    private final Charset charset;

    public TelegramHandler(String telegram) {
        charset = StandardCharsets.UTF_8;
        this.telegram = telegram.getBytes(charset);
    }
    public TelegramHandler(String telegram, Charset charset) {
        this.charset = charset;
        this.telegram = telegram.getBytes(charset);
    }

    public TelegramHandler(byte[] telegram, Charset charset) {
        this.charset = charset;
        this.telegram = telegram;
    }

    public String field(int beginIndex, int endIndex) {
        validIndex(beginIndex, endIndex);
        return extractField(beginIndex, endIndex);
    }

    private String extractField(int beginIndex, int endIndex) {
        try {
            return new String(extractByte(beginIndex, endIndex), charset);
        } catch (StringIndexOutOfBoundsException e) {
            throw new RuntimeException("전문길이와 index가 맞지 않습니다. telegram length: " + telegram.length + ", bedinIndex: " + beginIndex
                + ", endIndex: " + endIndex);
        }
    }

    private void validIndex(int beginIndex, int endIndex) {
        if (beginIndex < 1) {
            throw new RuntimeException("beginIndex는 0보다 커야합니다. telegram length: " + telegram.length + ", bedinIndex: " + beginIndex
                + ", endIndex: " + endIndex);
        }

        if (endIndex > telegram.length) {
            throw new RuntimeException("endIndex가 전문길이보다 큽니다. telegram length: " + telegram.length + ", bedinIndex: " + beginIndex
                + ", endIndex: " + endIndex);
        }

        if (beginIndex > endIndex) {
            throw new RuntimeException("endIndex는 beginIndex보다 커야합니다. telegram length: " + telegram.length + ", bedinIndex: " + beginIndex
                + ", endIndex: " + endIndex);
        }
    }

    private byte[] extractByte(int beginIndex, int endIndex) {
        int length = endIndex - beginIndex + 1;
        byte[] field = new byte[length];
        System.arraycopy(telegram, beginIndex - 1, field, 0, length);
        return field;
    }

    public int isLengthOf(int length) {
        return Integer.compare(length, telegram.length);
    }

    public void changeField(int beginIndex, int endIndex, String newField) {
        if (endIndex - beginIndex != newField.length() - 1) {
            throw new RuntimeException("새로운 필드의 길이는 이전 필드의 길이와 같아야 합니다.");
        }

        byte[] field = newField.getBytes(charset);
        System.arraycopy(field, 0, telegram, beginIndex - 1, endIndex - beginIndex + 1);
    }
}
