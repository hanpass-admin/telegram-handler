package im.juniq.telegram_handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.charset.Charset;
import org.junit.jupiter.api.Test;

class TelegramHandlerTest {
    public static final String TEST_TELEGRAM_WITH_KOREAN = "12345testname  한글 321     0000025500          ";

    @Test
    void 전문핸들러_생성() {
        assertThatCode(() -> new TelegramHandler(TEST_TELEGRAM_WITH_KOREAN)).doesNotThrowAnyException();
    }

    @Test
    void 전문길이_비교_UTF8() {
        TelegramHandler telegramHandler = new TelegramHandler(TEST_TELEGRAM_WITH_KOREAN);

        assertThat(telegramHandler.isLengthOf(50)).isEqualTo(0);
        assertThat(telegramHandler.isLengthOf(51)).isEqualTo(1);
        assertThat(telegramHandler.isLengthOf(49)).isEqualTo(-1);
    }

    @Test
    void 전문길이_비교_EUCKR() {
        TelegramHandler telegramHandler = new TelegramHandler(TEST_TELEGRAM_WITH_KOREAN, Charset.forName("EUC-KR"));

        assertThat(telegramHandler.isLengthOf(48)).isEqualTo(0);
        assertThat(telegramHandler.isLengthOf(49)).isEqualTo(1);
        assertThat(telegramHandler.isLengthOf(47)).isEqualTo(-1);
    }

    @Test
    void 특정필드_가져오기() {
        TelegramHandler telegramHandler = new TelegramHandler(TEST_TELEGRAM_WITH_KOREAN);

        assertThat(telegramHandler.field(1, 5)).isEqualTo("12345");
        assertThat(telegramHandler.field(6, 8)).isEqualTo("testname");
        assertThat(telegramHandler.field(1, 1)).isEqualTo("1");
        assertThat(telegramHandler.field(6, 1)).isEqualTo("t");
        assertThat(telegramHandler.field(16, 6)).isEqualTo("한글");
        assertThat(telegramHandler.field(23, 3)).isEqualTo("321");
    }

    @Test
    void 특정필드_가져오기_EUCKR() {
        TelegramHandler telegramHandler = new TelegramHandler(TEST_TELEGRAM_WITH_KOREAN, Charset.forName("EUC-KR"));

        assertThat(telegramHandler.field(1, 5)).isEqualTo("12345");
        assertThat(telegramHandler.field(6, 8)).isEqualTo("testname");
        assertThat(telegramHandler.field(1, 1)).isEqualTo("1");
        assertThat(telegramHandler.field(6, 1)).isEqualTo("t");
        assertThat(telegramHandler.field(16, 4)).isEqualTo("한글");
        assertThat(telegramHandler.field(21, 3)).isEqualTo("321");
    }

    @Test
    void 특정필드_가져올때_전문길이_예외() {
        TelegramHandler telegramHandler = new TelegramHandler(TEST_TELEGRAM_WITH_KOREAN);

        assertThatThrownBy(() -> telegramHandler.field(0, 5)).isInstanceOf(RuntimeException.class)
            .hasMessage("beginIndex는 0보다 커야합니다. telegram length: 50, bedinIndex: 0, fieldLength: 5");
        assertThatThrownBy(() -> telegramHandler.field(2, 0)).isInstanceOf(RuntimeException.class)
            .hasMessage("fieldLength는 0보다 커야합니다. telegram length: 50, bedinIndex: 2, fieldLength: 0");
        assertThatThrownBy(() -> telegramHandler.field(40, 51)).isInstanceOf(RuntimeException.class)
            .hasMessage("가져올 field의 index가 전문 길이보다 큽니다. telegram length: 50, bedinIndex: 40, fieldLength: 51");
    }

    @Test
    void 특정필드_값_바꾸기() {
        TelegramHandler telegramHandler = new TelegramHandler(TEST_TELEGRAM_WITH_KOREAN);

        telegramHandler.changeField(1, 5, "54321");

        assertThat(telegramHandler.field(1, 5)).isEqualTo("54321");
        assertThat(telegramHandler.field(6, 8)).isEqualTo("testname");
    }

    @Test
    void 모자란부분은_스페이스로_채워서_특정필드_값_바꾸기() {
        TelegramHandler telegramHandler = new TelegramHandler(TEST_TELEGRAM_WITH_KOREAN);

        telegramHandler.changeField(1, 5, "eee");

        assertThat(telegramHandler.field(1, 5)).isEqualTo("eee");
    }

    @Test
    void 모자란부분은_왼쪽에_스페이스로_채워서_특정필드_값_바꾸기() {
        TelegramHandler telegramHandler = new TelegramHandler(TEST_TELEGRAM_WITH_KOREAN);

        telegramHandler.changeFieldAndLeftFill(1, 5, "eee");

        assertThat(telegramHandler.originField(1, 5)).isEqualTo("  eee");
    }

    @Test
    void 특정필드_숫자로_가져오기() {
        TelegramHandler telegramHandler = new TelegramHandler(TEST_TELEGRAM_WITH_KOREAN);

        assertThat(telegramHandler.longField(31, 10)).isEqualTo(25500L);
    }

    @Test
    void 모자란부분은_0으로_채워서_숫자로_특정필드_값_바꾸기() {
        TelegramHandler telegramHandler = new TelegramHandler(TEST_TELEGRAM_WITH_KOREAN);

        telegramHandler.changeNumberField(31, 10, "77000");

        assertThat(telegramHandler.longField(31, 10)).isEqualTo(77000L);
    }

    @Test
    void 특정필드_값_바꾸기_필드길이_예외() {
        TelegramHandler telegramHandler = new TelegramHandler(TEST_TELEGRAM_WITH_KOREAN);

        assertThatThrownBy(() -> telegramHandler.changeField(1, 3, "5432")).isInstanceOf(RuntimeException.class)
            .hasMessage("새로운 필드의 길이는 이전 필드의 길이보다 작아야 합니다.");
    }
}
