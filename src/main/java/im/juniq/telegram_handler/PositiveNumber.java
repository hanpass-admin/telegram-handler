package im.juniq.telegram_handler;

public class PositiveNumber {
    private final String onlyNumber;

    private PositiveNumber(String onlyNumber) {
        this.onlyNumber = onlyNumber;
    }

    public static PositiveNumber ofLeftPadZero(String positiveNumber) {
        return new PositiveNumber(positiveNumber.replaceFirst("^0+(?!$)", ""));
    }

    public long longValue() {
        return Long.parseLong(onlyNumber);
    }

    public String leftPadZero(int digits) {
        return String.format("%0" + digits + "d", Integer.parseInt(onlyNumber));
    }
}
