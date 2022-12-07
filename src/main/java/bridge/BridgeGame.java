package bridge;

import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final static String commandToEnd = "Q";
    private final List<String> path = new ArrayList<>();
    private List<String> bridge;
    private int numberOfTry;

    BridgeGame() { initializeGame(); }

    public void run() {
        playRound();
        endGame();
    }

    private boolean isGameNotEnded() { return !(this.path.equals(this.bridge)); }

    private void initializeGame() {
        OutputView.announceStart();
        int bridgeSize = getBridgeSize();
        makeBridge(bridgeSize);
        this.numberOfTry = 1;
    }

    private int getBridgeSize() {
        OutputView.guideBridgeSize();
        try {
            return BridgeSize.get();
        } catch(IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return getBridgeSize();
    }

    private void makeBridge(int bridgeSize) {
        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
        this.bridge = bridgeMaker.makeBridge(bridgeSize);
    }

    private void playRound() {
        move();
        OutputView.printMap(this.path, this.bridge);
        while (isMoveWrong()) {
            if (wantToEnd()) return;
            retry();
            OutputView.printMap(this.path, this.bridge);
        }
        if (isGameNotEnded()) playRound();
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    private void move() {
        String moveCommand = getMoveCommand();
        this.path.add(moveCommand);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    private void retry() {
        this.path.remove(this.path.size() - 1);
        this.numberOfTry++;
        move();
    }

    private boolean isMoveWrong() {
        int currentPartitionIndex = this.path.size() - 1;
        String currentMove = this.path.get(currentPartitionIndex);
        String currentCorrectPosition = this.bridge.get(currentPartitionIndex);
        return !currentMove.equals(currentCorrectPosition);
    }

    private boolean wantToEnd() { return getGameCommand().equals(commandToEnd); }

    private String getMoveCommand() {
        OutputView.guideMoveCommand();
        try {
            return MoveCommand.get();
        } catch(IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return getMoveCommand();
    }

    private String getGameCommand() {
        OutputView.guideGameCommand();
        try {
            return GameCommand.get();
        } catch(IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return getGameCommand();
    }

    private void endGame() {
        OutputView.announceEnd();
        OutputView.printMap(this.path, this.bridge);
        OutputView.printResult(!isGameNotEnded(), this.numberOfTry);
    }
}
