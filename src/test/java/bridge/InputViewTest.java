package bridge;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;

class InputViewTest extends NsTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    @DisplayName("parseInt 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"100a", "100000000000", "*"})
    void 다리_길이_예외_테스트(String input) {
        assertSimpleTest(() -> {
            runException(input);
            int bridgeSize = new InputView().readBridgeSize();
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("parseInt 정상 입력 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-1000", "365", "3", "20"})
    void 다리_길이_정상_입력_테스트(String input) {
        assertSimpleTest(() -> {
            run(input);
            int bridgeSize = new InputView().readBridgeSize();
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
