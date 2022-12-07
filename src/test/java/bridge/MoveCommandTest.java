package bridge;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoveCommandTest extends NsTest {

    @DisplayName("U와 D를 제외한 모든 입력에 대해 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"R", "Q", "0414kjh@naver.com", "neppiness", "3", "20"})
    void bridgeSizeExceptionTest(String wrongInput) {
        assertSimpleTest(() -> {
            runException(wrongInput);
            assertThatThrownBy(() -> { MoveCommand.get(); })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("U 또는 D를 입력해주세요.");
        });
    }

    @DisplayName("이동 명령 정상 입력 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"U", "D"})
    void bridgeSizeTest(String correctInput) {
        assertSimpleTest(() -> {
            run(correctInput);
            MoveCommand.get();
        });
    }

    @Override
    protected void runMain() {}
}
