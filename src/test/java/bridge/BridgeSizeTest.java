package bridge;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BridgeSizeTest extends NsTest {

    @DisplayName("정수로 파싱이 불가능한 입력이 주어지는 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"*", "neppiness", "@(*#&", "0414kjh@naver.com", "24"})
    void nonIntegerInputExceptionTest(String intOutOfRange) {
        assertSimpleTest(() -> {
            runException(intOutOfRange);
            assertThatThrownBy(() -> { BridgeSize.get(); })
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @DisplayName("3 이상 20 이하 자연수가 아닌 입력에 대해서 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "0", "1", "2", "21", "22", "23", "24"})
    void bridgeSizeExceptionTest(String intOutOfRange) {
        assertSimpleTest(() -> {
            runException(intOutOfRange);
            assertThatThrownBy(() -> { BridgeSize.get(); })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("3 이상 20 이하의 자연수를 입력해야 합니다.");
        });
    }

    @DisplayName("다리 길이 정상 입력 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"})
    void bridgeSizeTest(String correctInput) {
        assertSimpleTest(() -> {
            run(correctInput);
            BridgeSize.get();
        });
    }

    @Override
    protected void runMain() {}
}
