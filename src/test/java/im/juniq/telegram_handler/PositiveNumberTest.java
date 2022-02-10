package im.juniq.telegram_handler;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositiveNumberTest {
    @Test
    void _0_채운_양수_파싱() {
        PositiveNumber positiveNumber = PositiveNumber.ofLeftPadZero("0000025500");
        assertThat(positiveNumber.longValue()).isEqualTo(25500L);
    }

    @Test
    void _0_만_있을때() {
        PositiveNumber positiveNumber = PositiveNumber.ofLeftPadZero("0000000000");
        assertThat(positiveNumber.longValue()).isEqualTo(0L);
    }

    @Test
    void 양수_0_채우기() {
        PositiveNumber positiveNumber = PositiveNumber.ofLeftPadZero("25500");
        assertThat(positiveNumber.leftPadZero(7)).isEqualTo("0025500");
    }
}
