package bridge;

/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class MoveCommand {

    private static final String[] correctMoveCommands = {"U", "D"};

    public static String get() {
        String moveCommand = InputView.readMoving();
        validate(moveCommand);
        return moveCommand;
    }

    private static void validate(String moveCommand) {
        for (String correctMoveCommand : correctMoveCommands)
            if (moveCommand.equals(correctMoveCommand)) return;
        throw new IllegalArgumentException("U 또는 D를 입력해주세요.");
    }
}
