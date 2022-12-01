package bridge;

import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    final static OutputView outputView = new OutputView();
    final static MovingCommand movingCommand = new MovingCommand();
    final static GameCommand gameCommand = new GameCommand();
    final static BridgeSize bridgeSize = new BridgeSize();

    List<String> bridge;
    List<String> path = new ArrayList<>();
    int numberOfTry = 1;

    BridgeGame() {
        outputView.announceStart();
        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        int bridgeSize = getBridgeSize();
        this.bridge = new BridgeMaker(bridgeNumberGenerator).makeBridge(bridgeSize);
    }

    public void run() {
        tryCycle();
        outputView.announceEnd();
        outputView.printResult(this.bridge, this.path, numberOfTry, isGameEnded());
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     */
    private void move() {
        outputView.guideMove();
        String command = movingCommand.get();
        this.path.add(command);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     */
    private void retry() {
        numberOfTry++;
        path.remove(path.size() - 1);
        move();
    }

    private void tryCycle() {
        move();
        outputView.printMap(this.bridge, this.path);
        while(isWrongMove()) {
            if (wantToExit(gameCommand.get())) return;
            retry();
            outputView.printMap(this.bridge, this.path);
        }
        if(!isGameEnded()) tryCycle();
    }

    private int getBridgeSize() {
        try {
            return bridgeSize.get();
        } catch(IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
        return getBridgeSize();
    }

    private boolean isWrongMove() {
        int currentPosition = path.size() - 1;
        String currentBridgePartition = this.bridge.get(currentPosition);
        String currentPath = this.path.get(currentPosition);
        return !currentBridgePartition.equals(currentPath);
    }

    private boolean isGameEnded() { return path.equals(bridge); }

    private boolean wantToExit(String gameCommand) { return gameCommand.equals("Q"); }
}
