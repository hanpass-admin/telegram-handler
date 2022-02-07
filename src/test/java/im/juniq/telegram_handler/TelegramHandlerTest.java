package im.juniq.telegram_handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

class TelegramHandlerTest {
    @Test
    void 전문핸들러_생성() {
        assertThatCode(() -> new TelegramHandler("12345testname")).doesNotThrowAnyException();
    }

    @Test
    void 전문길이_비교() {
        TelegramHandler telegramHandler = new TelegramHandler("12345testname");
        assertThat(telegramHandler.isLengthOf(13)).isEqualTo(0);
        assertThat(telegramHandler.isLengthOf(14)).isEqualTo(1);
        assertThat(telegramHandler.isLengthOf(12)).isEqualTo(-1);
    }

    @Test
    void 전문_특정필드_가져오기() {
        TelegramHandler telegramHandler = new TelegramHandler("12345testname");
        assertThat(telegramHandler.field(1, 5)).isEqualTo("12345");
    }

    @Test
    void 전문_특정필드_가져오기2() {
        TelegramHandler telegramHandler = new TelegramHandler("12345testname");
        assertThat(telegramHandler.field(6, 13)).isEqualTo("testname");
    }

    @Test
    void 전문_특정필드_가져올때_예외() {
        TelegramHandler telegramHandler = new TelegramHandler("12345testname");
        assertThat(telegramHandler.field(1, 5)).isEqualTo("12345");
    }
}