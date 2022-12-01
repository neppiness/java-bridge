package bridge;

import java.util.List;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class MovingCommand {

    static final List<String> validCommands = List.of(new String[]{"U", "D"});

    public String get() {
        String movingCommand = new InputView().readMoving();
        validate(movingCommand);
        return movingCommand;
    }

    private void validate(String inputCommand) {
        for (String validCommand : validCommands)
            if (inputCommand.equals(validCommand)) return;
        throw new IllegalArgumentException("U 또는 D를 입력해주세요.");
    }
}
