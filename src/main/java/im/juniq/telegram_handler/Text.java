package im.juniq.telegram_handler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Text {
    private final byte[] text;
    private final Charset charset;

    private Text(byte[] text, Charset charset) {
        this.text = text;
        this.charset = charset;
    }

    public static Text ofRightPad(String text, int length) {
        return ofRightPad(text, length, StandardCharsets.UTF_8);
    }

    public static Text ofLeftPad(String text, int length) {
        return ofLeftPad(text, length, StandardCharsets.UTF_8);
    }

    public static Text ofRightPad(String text, int length, Charset charset) {
        byte[] byteText = text.getBytes(charset);
        int padLength = length - byteText.length;
        if (padLength < 0) {
            throw new RuntimeException("문자열 바이트의 길이가 length 보다 큽니다. text: " + text + ", length: " + length);
        }
        return new Text((text + makePad(padLength)).getBytes(charset), charset);
    }

    public static Text ofLeftPad(String text, int length, Charset charset) {
        byte[] byteText = text.getBytes(charset);
        int padLength = length - byteText.length;
        if (padLength < 0) {
            throw new RuntimeException("문자열 바이트의 길이가 length 보다 큽니다. text: " + text + ", length: " + length);
        }
        return new Text((makePad(padLength) + text).getBytes(charset), charset);
    }

    private static String makePad(int length) {
        return " ".repeat(length);
    }

    public byte[] value() {
        return text;
    }

    public String stringValue() {
        return new String(text, charset);
    }
}
