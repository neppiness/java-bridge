package bridge;

import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    private static final String ERROR_MESSAGE = "[ERROR] ";

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(List<String> bridge, List<String> path) {
        // TODO: 맵 출력 로직 구현
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult() {
    }

    public void printErrorMessage(String message) {
        System.out.println(ERROR_MESSAGE + message);
    }
    public void printMessage(String message) { System.out.println(message); }

    public void announceStart() { printMessage("다리 건너기 게임을 시작합니다."); }
    public void announceEnd() { printMessage("최종 게임 결과"); }

    public void announceResult(int numberOfTry, boolean isCorrect) {
        String passOrFail = "실패";
        if (isCorrect) passOrFail = "성공";
        printMessage("게임 성공 여부: " + passOrFail);
        printMessage(String.format("총 시도한 횟수: %d", numberOfTry));
    }

    public void guideMove() { printMessage("이동할 칸을 선택해주세요. (위: U, 아래: D)"); }
}
