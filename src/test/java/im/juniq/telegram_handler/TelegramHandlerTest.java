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
    void 전문길이_조회() {
        TelegramHandler telegramHandler = new TelegramHandler("12345testname");
        assertThat(telegramHandler.length()).isEqualTo(13);
    }

    @Test
    void 전문_특정필드_가져오기() {
        TelegramHandler telegramHandler = new TelegramHandler("12345testname");
        assertThat(telegramHandler.get(0, 5)).isEqualTo("12345");
    }
}