package bridge;

import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class BridgeSize {

    int getValue() {
        int bridgeSize = new InputView().readBridgeSize();
        validateRange(bridgeSize);
        return bridgeSize;
    }

    private void validateRange(int bridgeSize) {
        if (bridgeSize < 3 || bridgeSize > 20)
            throw new IllegalArgumentException("3이상 20이하의 자연수를 입력해주세요.");
    }
}
