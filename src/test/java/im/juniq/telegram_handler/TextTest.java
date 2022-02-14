package im.juniq.telegram_handler;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TextTest {
    @Test
    void 오른쪽_빈문자_패딩_영문() {
        Text text = Text.ofRightPad("text", 10);
        assertThat(text.value().length).isEqualTo(10);
    }

    @Test
    void 오른쪽_빈문자_패딩_한글() {
        Text text = Text.ofRightPad("한글", 10);
        assertThat(text.value().length).isEqualTo(10);
    }

    @Test
    void 왼쪽_빈문자_패딩_영문() {
        Text text = Text.ofLeftPad("text", 10);
        assertThat(text.value().length).isEqualTo(10);
    }

    @Test
    void 왼쪽_빈문자_패딩_한글() {
        Text text = Text.ofLeftPad("한글", 10);
        assertThat(text.value().length).isEqualTo(10);
    }

    @Test
    void 변환된_바이트길이가_지정한_길이보다_클때_예외() {
        assertThatThrownBy(() -> Text.ofRightPad("한글한글", 10)).isInstanceOf(RuntimeException.class)
                .hasMessage("문자열 바이트의 길이가 length 보다 큽니다. text: 한글한글, length: 10");
    }

    @Test
    void 왼쪽_영_패딩_숫자() {
        Text text = Text.ofZeroPad("123", 10);
        assertThat(text.value().length).isEqualTo(10);
    }
}
