package bridge;

import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    private String lineInput;

    InputView() {
        this.lineInput = Console.readLine();
    }

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        int number;
        try {
            number = Integer.parseInt(this.lineInput);
        } catch (NumberFormatException e) {
            new OutputView().printErrorMessage("3이상 20이하의 자연수를 입력해주세요.");
            return -1;
        }
        return number;
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        return lineInput;
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        return lineInput;
    }
}
