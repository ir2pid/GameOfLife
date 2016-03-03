package noisyninja.com.gameoflifesim.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import noisyninja.com.gameoflifesim.Utils;

/**
 * Class to hold all game logic and touch interactions
 * Created by ir2pid on 02/03/16.
 */
public class GameLogic extends GameGrid {

    protected boolean isRunning = false;

    public GameLogic(Context context) {
        super(context);
    }

    public GameLogic(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameLogic(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameLogic(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * override the onTouchEvent
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                upTouch(x, y);
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * when ACTION_UP stop touch
     *
     * @param x x coordinate of touch
     * @param y y coordinate of touch
     */
    private void upTouch(float x, float y) {
        mX = x;
        mY = y;

        if (!isRunning) {//no selecton when simulation is on
            GameUtils.selectCell(gameCells, x, y);
        }

    }


    /**
     * iterates each cell and determines next generation state
     */
    protected void nextGen() {

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                gameCellsTemp[i][j] = isAlive(i, j, gameCells);
            }
        }

        copyToCells(gameCells, gameCellsTemp);
    }

    /**
     * determines if cell at i,j is alive in next generation considering
     * its neighbouring cells
     *
     * @param i x position of cell
     * @param j y position of cell
     * @return
     */
    private boolean isAlive(int i, int j, GameCell[][] gameCells) {

        int count = 0;

        //possible neighbour x cordinates, -1 if beyond edge
        int x1 = i - 1 >= 0 ? i - 1 : -1;
        int x2 = i;
        int x3 = i + 1 <= 19 ? i + 1 : -1;

        //possible neighbour y cordinates, -1 if beyond edge
        int y1 = j - 1 >= 0 ? j - 1 : -1;
        int y2 = j;
        int y3 = j + 1 <= 19 ? j + 1 : -1;

        if (x1 != -1) { //check top row
            if (y1 != -1 && gameCells[x1][y1].isAlive()) {
                count++;
            }
            if (y2 != -1 && gameCells[x1][y2].isAlive()) {
                count++;
            }
            if (y3 != -1 && gameCells[x1][y3].isAlive()) {
                count++;
            }
        }

        //check middle row
        if (y1 != -1 && gameCells[x2][y1].isAlive()) {
            count++;
        }
        if (y3 != -1 && gameCells[x2][y3].isAlive()) {
            count++;
        }

        //check bottom row
        if (x3 != -1) {
            if (y1 != -1 && gameCells[x3][y1].isAlive()) {
                count++;
            }
            if (y2 != -1 && gameCells[x3][y2].isAlive()) {
                count++;
            }
            if (y3 != -1 && gameCells[x3][y3].isAlive()) {
                count++;
            }
        }

        //apply alive logic
        if (gameCells[i][j].isAlive()) {
            if (count < 2)  //under population
            {
                Utils.logD(getClass(), "under population killed:" + i + "," + j + ", neighbour count:" + count);
                return false;
            } else if (count > 3) //over population
            {
                Utils.logD(getClass(), "over population killed " + i + "," + j + ", neighbour count:" + count);
                return false;
            } else { //between 2&3, alive and continue
                Utils.logD(getClass(), "continue " + i + "," + j + ", neighbour count:" + count);
                return true;
            }
        } else if (!gameCells[i][j].isAlive() && count == 3) { // 3 live neighbours, dead re-spawns
            Utils.logD(getClass(), "spawn " + i + "," + j + ", neighbour count:" + count);
            return true;
        }

        return false;//no change, already dead

    }

    /**
     * copy next generation cell state to gamecells array
     */
    private void copyToCells(GameCell[][] gameCells, boolean[][] gameCellsTemp) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                gameCells[i][j].setIsAlive(gameCellsTemp[i][j]);
            }
        }
    }


    //getter - setters below

    public boolean isRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

}
