package bridge;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class BridgeSize {

    private static final OutputView outputView = new OutputView();

    public int get() {
        outputView.print("다리의 길이를 입력해주세요.");
        try {
            int bridgeSize = new InputView().readBridgeSize();
            validateRange(bridgeSize);
            return bridgeSize;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
        return get();
    }

    private void validateRange(int bridgeSize) {
        if (bridgeSize < 3 || bridgeSize > 20)
            throw new IllegalArgumentException("3이상 20이하의 자연수를 입력해주세요.");
    }
}
