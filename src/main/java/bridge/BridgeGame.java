package bridge;

import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    final static OutputView outputView = new OutputView();
    final static MovingCommand movingCommand = new MovingCommand();
    final static GameCommand gameCommand = new GameCommand();

    List<String> bridge, path;
    int numberOfTry = 0;

    BridgeGame() {
        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        int bridgeSize = new BridgeSize().get();
        this.bridge = new BridgeMaker(bridgeNumberGenerator).makeBridge(bridgeSize);
    }
    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move() {
        outputView.guideMove();
        String command = movingCommand.get();
        path.add(command);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        numberOfTry++;
        path.remove(path.size() - 1);
        move();
    }

    public void run() {
        outputView.announceStart();
        tryCycle();
        outputView.announceEnd();
        outputView.printMap(this.bridge, this.path);
        outputView.announceResult(numberOfTry, isGameEnded());
    }

    public void tryCycle() {
        move();
        outputView.printMap(this.bridge, this.path);
        while(isWrongMove()) {
            if (wantToExit(gameCommand.get())) return;
            retry();
            outputView.printMap(this.bridge, this.path);
        }
        if(!isGameEnded()) tryCycle();
    }

    private boolean isWrongMove() {
        int currentPosition = path.size() - 1;
        String currentBridgePartition = this.bridge.get(currentPosition);
        String currentPath = this.path.get(currentPosition);
        return currentBridgePartition.equals(currentPath);
    }

    private boolean isGameEnded() { return path.equals(bridge); }

    private boolean wantToExit(String gameCommand) { return gameCommand.equals("Q"); }
}
