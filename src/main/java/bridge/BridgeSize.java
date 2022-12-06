package bridge;

/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class BridgeSize {

    public static int get() {
        int bridgeSize = InputView.readBridgeSize();
        validate(bridgeSize);
        return bridgeSize;
    }

    private static void validate(int bridgeSize) {
        if (bridgeSize >= 3 && bridgeSize <= 20) return;
        throw new IllegalArgumentException("3 이상 20 이하의 자연수를 입력해야 합니다.");
    }
}
