package noisyninja.com.gameoflifesim.components;

/**
 * Game engine to update loop and refresh view
 * Created by ir2pid on 01/03/16.
 */
public class GameEngine {

    public static final int FPS = 1;
    private GameLogic gameLogic;
    // This is used to help calculate the fps
    //private long timeThisFrame;
    // This variable tracks the game frame rate
    //long fps;

    public GameEngine(GameLogic gameGrid) {
        this.gameLogic = gameGrid;
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    /**
     * method to update game loop
     */
    public void update() {

        if (gameLogic.isRunning()) {
            gameLogic.nextGen();
            /* no need to calculate fps
            long startFrameTime = System.currentTimeMillis();
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame > 0) {
                fps = 1000 / timeThisFrame;
            }*/
        }
    }

    /**
     * method to refresh canvas and draw new state of cells
     */
    public void refresh(){
        gameLogic.invalidate();
    }

}
