package bridge;

import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    /**
     * 다리의 길이를 입력받는다.
     */
    public static int readBridgeSize() {
        String lineInput = getInputLine();
        return Integer.parseInt(lineInput);
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public static String readMoving() { return getInputLine(); }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public static String readGameCommand() { return getInputLine(); }

    private static String getInputLine() {
        String lineInput = Console.readLine();
        checkIfInputBlank(lineInput);
        return lineInput;
    }

    private static void checkIfInputBlank(String lineInput) {
        if (lineInput.isBlank())
            throw new IllegalArgumentException("공백 문자만을 입력할 수 없습니다.");
    }
}
