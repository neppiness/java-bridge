package bridge;

import java.util.List;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class GameCommand {

    private final static List<String> validCommands = List.of(new String[]{"R", "Q"});
    private final static OutputView outputview = new OutputView();

    public String get() {
        outputview.guideGameCommand();
        try {
            String gameCommand = new InputView().readGameCommand();
            validate(gameCommand);
            return gameCommand;
        } catch(IllegalArgumentException e) {
            outputview.printErrorMessage(e.getMessage());
        }
        return get();
    }

    private void validate(String inputCommand) {
        for (String validCommand : validCommands)
            if (validCommand.equals(inputCommand)) return;
        throw new IllegalArgumentException(String.format("%s 또는 %s를 입력해주세요.",
                validCommands.get(0), validCommands.get(1)));
    }
}
