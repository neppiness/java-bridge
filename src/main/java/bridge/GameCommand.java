package bridge;

/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class GameCommand {

    private static final String[] correctGameCommands = {"R", "Q"};

    public static String get() {
        String gameCommand = InputView.readGameCommand();
        validate(gameCommand);
        return gameCommand;
    }

    private static void validate(String gameCommand) {
        for (String correctGameCommand : correctGameCommands)
            if (gameCommand.equals(correctGameCommand)) return;
        throw new IllegalArgumentException("R 또는 Q를 입력해주세요.");
    }
}
