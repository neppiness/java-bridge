package bridge;

import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    private static final String ERROR_MESSAGE = "[ERROR] ";

    public void printErrorMessage(String message) {
        System.out.println(ERROR_MESSAGE + message);
    }

    public void print(String string) { System.out.println(string); }

    public void announceStart() { print("다리 건너기 게임을 시작합니다."); }

    public void announceEnd() { print("최종 게임 결과"); }

    public void announceResult(int numberOfTry, boolean isCorrect) {
        String passOrFail = "실패";
        if (isCorrect) passOrFail = "성공";
        print("게임 성공 여부: " + passOrFail);
        print(String.format("총 시도한 횟수: %d", numberOfTry));
    }

    public void guideMove() { print("이동할 칸을 선택해주세요. (위: U, 아래: D)"); }

    public void guideGameCommand() { print("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)"); }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(List<String> bridge, List<String> path, int numberOfTry, boolean isCorrect) {
        printMap(bridge, path);
        announceResult(numberOfTry, isCorrect);
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(List<String> bridge, List<String> path) {
        List<String> levels = List.of(new String[]{"U", "D"});
        for (String level : levels)
            printMapByLevel(bridge, path, level);
    }

    private void printMapByLevel(List<String> bridge, List<String> path, String level) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int index = 0; index < path.size(); index++) {
            if (index != 0) stringBuilder.append("|");
            if (!path.get(index).equals(level)) {
                stringBuilder.append("   ");
                continue;
            }
            if (path.get(index).equals(bridge.get(index))) {
                stringBuilder.append(" O ");
                continue;
            }
            stringBuilder.append(" X ");
        }
        stringBuilder.append("]");
        print(stringBuilder.toString());
    }
}
