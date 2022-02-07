package im.juniq.telegram_handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void 특정필드_가져오기() {
        TelegramHandler telegramHandler = new TelegramHandler("12345testname");
        assertThat(telegramHandler.field(1, 5)).isEqualTo("12345");
    }

    @Test
    void 특정필드_가져오기2() {
        TelegramHandler telegramHandler = new TelegramHandler("12345testname");
        assertThat(telegramHandler.field(6, 13)).isEqualTo("testname");
    }

    @Test
    void 특정필드_가져올때_전문길이_예외() {
        TelegramHandler telegramHandler = new TelegramHandler("12345testname");
        assertThatThrownBy(() -> telegramHandler.field(6, 14)).isInstanceOf(RuntimeException.class)
            .hasMessage("전문길이와 index가 맞지 않습니다. telegram length: 13, bedinIndex: 6, endIndex: 14");
    }

    @Test
    void 특정필드_가져올때_전문길이_예외2() {
        TelegramHandler telegramHandler = new TelegramHandler("12345testname");
        assertThatThrownBy(() -> telegramHandler.field(0, 14)).isInstanceOf(RuntimeException.class)
            .hasMessage("beginIndex는 0보다 커야합니다. telegram length: 13, bedinIndex: 0, endIndex: 14");
    }

    @Test
    void 특정필드_값_바꾸기() {
        TelegramHandler telegramHandler = new TelegramHandler("12345testname");

        telegramHandler.changeField(1, 5, "54321");

        assertThat(telegramHandler.field(1, 5)).isEqualTo("54321");
        assertThat(telegramHandler.field(6, 13)).isEqualTo("testname");
    }

    @Test
    void 특정필드_값_바꾸기2() {
        TelegramHandler telegramHandler = new TelegramHandler("12345testname");

        telegramHandler.changeField(6, 9, "eeee");

        assertThat(telegramHandler.field(1, 5)).isEqualTo("12345");
        assertThat(telegramHandler.field(6, 13)).isEqualTo("eeeename");
    }

    @Test
    void 특정필드_값_바꾸기_필드길이_예외() {
        TelegramHandler telegramHandler = new TelegramHandler("12345testname");

        assertThatThrownBy(() -> telegramHandler.changeField(1, 5, "5432")).isInstanceOf(RuntimeException.class)
            .hasMessage("새로운 필드의 길이는 이전 필드의 길이와 같아야 합니다.");
    }
}
