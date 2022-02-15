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

    public String field(int beginIndex, int fieldLength) {
        validIndex(beginIndex, fieldLength);
        return extractField(beginIndex, fieldLength);
    }

    private String extractField(int beginIndex, int fieldLength) {
        try {
            return new String(extractByte(beginIndex, fieldLength), charset);
        } catch (StringIndexOutOfBoundsException e) {
            throw new RuntimeException("전문길이와 index가 맞지 않습니다. telegram length: " + telegram.length + ", bedinIndex: " + beginIndex
                + ", fieldLength: " + fieldLength);
        }
    }

    private void validIndex(int beginIndex, int fieldLength) {
        if (beginIndex < 1) {
            throw new RuntimeException("beginIndex는 0보다 커야합니다. telegram length: " + telegram.length + ", bedinIndex: " + beginIndex
                + ", fieldLength: " + fieldLength);
        }

        if (fieldLength < 1) {
            throw new RuntimeException("fieldLength는 0보다 커야합니다. telegram length: " + telegram.length + ", bedinIndex: " + beginIndex
                + ", fieldLength: " + fieldLength);
        }

        if (beginIndex + fieldLength - 1  > telegram.length) {
            throw new RuntimeException("가져올 field의 index가 전문 길이보다 큽니다. telegram length: " + telegram.length + ", bedinIndex: " + beginIndex
                + ", fieldLength: " + fieldLength);
        }
    }

    private byte[] extractByte(int beginIndex, int fieldLength) {
        byte[] field = new byte[fieldLength];
        System.arraycopy(telegram, beginIndex - 1, field, 0, fieldLength);
        return field;
    }

    public int isLengthOf(int length) {
        return Integer.compare(length, telegram.length);
    }

    public void changeField(int beginIndex, int fieldLength, String newField) {
        if (fieldLength != newField.length()) {
            throw new RuntimeException("새로운 필드의 길이는 이전 필드의 길이와 같아야 합니다.");
        }

        byte[] field = newField.getBytes(charset);
        System.arraycopy(field, 0, telegram, beginIndex - 1, fieldLength);
    }

    public long longField(int beginIndex, int fieldLength) {
        return PositiveNumber.ofLeftPadZero(field(beginIndex, fieldLength)).longValue();
    }
}
