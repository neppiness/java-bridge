package bridge;

import java.util.List;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class MovingCommand {

    static final List<String> movingCommands = List.of(new String[]{"U", "D"});

    public String get() {
        String inputString = new InputView().readMoving();
        validate(inputString);
        return inputString;
    }

    private void validate(String inputString) {
        for (String moving : movingCommands)
            if (inputString.equals(movingCommands)) return;
        throw new IllegalArgumentException("U 또는 D를 입력해주세요.");
    }
}
