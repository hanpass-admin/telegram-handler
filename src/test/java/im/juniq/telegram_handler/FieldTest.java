package im.juniq.telegram_handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class FieldTest {
    @Test
    void 오른쪽_빈문자_패딩_영문() {
        Field field = Field.ofRightPad("text", 10);
        assertThat(field.value().length).isEqualTo(10);
    }

    @Test
    void 오른쪽_빈문자_패딩_한글() {
        Field field = Field.ofRightPad("한글", 10);
        assertThat(field.value().length).isEqualTo(10);
    }

    @Test
    void 왼쪽_빈문자_패딩_영문() {
        Field field = Field.ofLeftPad("text", 10);
        assertThat(field.value().length).isEqualTo(10);
    }

    @Test
    void 왼쪽_빈문자_패딩_한글() {
        Field field = Field.ofLeftPad("한글", 10);
        assertThat(field.value().length).isEqualTo(10);
    }

    @Test
    void 변환된_바이트길이가_지정한_길이보다_클때_예외() {
        assertThatThrownBy(() -> Field.ofRightPad("한글한글", 10)).isInstanceOf(RuntimeException.class)
                .hasMessage("문자열 바이트의 길이가 length 보다 큽니다. text: 한글한글, length: 10");
    }

    @Test
    void 왼쪽_영_패딩_숫자() {
        Field field = Field.ofZeroPad("123", 10);
        assertThat(field.value().length).isEqualTo(10);
    }
}
