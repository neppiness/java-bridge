package bridge;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputViewTest extends NsTest {

    @DisplayName("readBridgeSize 메소드의 공백 문자 입력은 예외 처리된다.")
    @ParameterizedTest
    @ValueSource(strings = {"\t", "  "})
    void readBridgeSizeMethodExceptionTest(String emptyInput) {
        assertSimpleTest(() -> {
            runException(emptyInput);
            assertThatThrownBy(() -> { InputView.readBridgeSize(); })
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @DisplayName("readMoving 메소드의 공백 문자 입력은 예외 처리된다.")
    @ParameterizedTest
    @ValueSource(strings = {"\t", "  "})
    void readMovingExceptionTest(String emptyInput) {
        assertSimpleTest(() -> {
            runException(emptyInput);
            assertThatThrownBy(() -> { InputView.readMoving(); })
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @DisplayName("readBridgeSize 메소드에 정수로 파싱이 불가능한 입력이 주어지면 예외 처리된다.")
    @ParameterizedTest
    @ValueSource(strings = {"*", "neppiness", "@(*#&", "0414kjh@naver.com", "-2147483649", "2147483648"})
    void nonIntegerInputExceptionTest(String inputImpossibleToBeParsedAsInt) {
        assertSimpleTest(() -> {
            runException(inputImpossibleToBeParsedAsInt);
            assertThatThrownBy(() -> { InputView.readBridgeSize(); })
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @DisplayName("다리 길이 정상 입력 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"-2147483648", "2147483647",
            "1", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"})
    void bridgeSizeTest(String correctInput) {
        assertSimpleTest(() -> {
            run(correctInput);
            InputView.readBridgeSize();
        });
    }

    @Override
    protected void runMain() {}
}
