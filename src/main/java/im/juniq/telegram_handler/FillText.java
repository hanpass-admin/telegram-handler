package im.juniq.telegram_handler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FillText {
    private final byte[] text;
    private final Charset charset;

    private FillText(byte[] text, Charset charset) {
        this.text = text;
        this.charset = charset;
    }

    public static FillText ofRightPad(String text, int length) {
        return ofRightPad(text, length, StandardCharsets.UTF_8);
    }

    public static FillText ofRightPad(String text, int length, Charset charset) {
        byte[] byteText = text.getBytes(charset);
        int padLength = length - byteText.length;
        if (padLength < 0) {
            throw new RuntimeException("문자열 바이트의 길이가 length 보다 큽니다. text: " + text + ", length: " + length);
        }
        String pads = makePad(padLength, " ");
        return new FillText((text + pads).getBytes(charset), charset);
    }

    public static FillText ofLeftPad(String text, int length) {
        return ofLeftPad(text, length, StandardCharsets.UTF_8);
    }

    public static FillText ofLeftPad(String text, int length, Charset charset) {
        byte[] byteText = text.getBytes(charset);
        int padLength = length - byteText.length;
        if (padLength < 0) {
            throw new RuntimeException("문자열 바이트의 길이가 length 보다 큽니다. text: " + text + ", length: " + length);
        }
        String pads = makePad(padLength, " ");
        return new FillText((pads + text).getBytes(charset), charset);
    }

    public static FillText ofZeroPad(String text, int length) {
        return ofZeroPad(text, length, StandardCharsets.UTF_8);
    }

    public static FillText ofZeroPad(String text, int length, Charset charset) {
        byte[] byteText = text.getBytes(charset);
        int padLength = length - byteText.length;
        if (padLength < 0) {
            throw new RuntimeException("문자열 바이트의 길이가 length 보다 큽니다. text: " + text + ", length: " + length);
        }
        String pads = makePad(padLength, "0");
        return new FillText((pads + text).getBytes(charset), charset);
    }

    private static String makePad(int length, String padding) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(padding);
        }
        return result.toString();
    }

    public byte[] value() {
        return text;
    }

    public String stringValue() {
        return new String(text, charset);
    }
}
