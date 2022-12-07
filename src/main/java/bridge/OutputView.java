package bridge;

import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    enum Level {
        UPPERLEVEL("U"),
        LOWERLEVEL("D");

        String command;
        Level (String command) { this.command = command; }
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public static void printMap(List<String> path, List<String> bridge) {
        printLine(upperLevelMap(path, bridge));
        printLine(lowerLevelMap(path, bridge));
    }

    private static String upperLevelMap(List<String> path, List<String> bridge) {
        return mapAsString(path, bridge, Level.UPPERLEVEL.command);
    }

    private static String lowerLevelMap(List<String> path, List<String> bridge) {
        return mapAsString(path, bridge, Level.LOWERLEVEL.command);
    }

    private static String mapAsString(List<String> path, List<String> bridge, String command) {
        StringBuilder stringBuilder = new StringBuilder().append("[");
        for (int partitionIndex = 0; partitionIndex < path.size(); partitionIndex++) {
            String partitionMap = getPartitionMap(partitionIndex, path, bridge, command);
            stringBuilder.append(partitionMap);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private static String getPartitionMap(int partitionIndex, List<String> path, List<String> bridge, String command) {
        StringBuilder stringBuilder = new StringBuilder();
        if (partitionIndex != 0) stringBuilder.append("|");
        String currentMove = path.get(partitionIndex);
        String currentBridgePartition = bridge.get(partitionIndex);
        String sign = getSign(currentMove, currentBridgePartition, command);
        stringBuilder.append(" ").append(sign).append(" ");
        return stringBuilder.toString();
    }

    private static String getSign(String currentMove, String currentBridgePartition, String command) {
        if (!currentMove.equals(command)) return " ";
        if (currentMove.equals(currentBridgePartition)) return "O";
        return "X";
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public static void printResult(boolean isSuccess, int numberOfTry) {
        String passOrFail = "실패";
        if(isSuccess) passOrFail = "성공";
        printLine("게임 성공 여부: " + passOrFail);
        printLine(String.format("총 시도한 횟수: %d", numberOfTry));
    }

    public static void announceStart() { printLine("다리 건너기 게임을 시작합니다."); }

    public static void announceEnd() { printLine("최종 게임 결과"); }

    public static void guideMoveCommand() { printLine("이동할 칸을 선택해주세요. (위: U, 아래: D)"); }

    public static void guideBridgeSize() { printLine("다리의 길이를 입력해주세요. (3 이상 20 이하의 자연수)"); }

    public static void guideGameCommand() { printLine("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)"); }

    public static void printErrorMessage(String errorMessage) { printLine("[ERROR] " + errorMessage); }

    private static void printLine(String message) { System.out.println(message); }
}
