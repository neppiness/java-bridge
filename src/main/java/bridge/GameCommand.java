package bridge;

import java.util.List;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class GameCommand {

    final static List<String> validCommands = List.of(new String[]{"R", "Q"});

    String get() {
        String gameCommand = new InputView().readGameCommand();
        validate(gameCommand);
        return gameCommand;
    }

    private void validate(String inputCommand) {
        for (String validCommand : validCommands)
            if (validCommand.equals(inputCommand)) return;
        throw new IllegalArgumentException("R 또는 Q를 입력해주세요.");
    }
}
