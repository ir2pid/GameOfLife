package noisyninja.com.gameoflifesim.components;

import android.graphics.Point;
import android.graphics.Rect;

import noisyninja.com.gameoflifesim.Utils;

/**
 * Contains Game related utility methods
 * Created by ir2pid on 03/03/16.
 */
public class GameUtils {

    /**
     * initializes the grid depending on screen dimentions and returns a single cell size
     *
     * @param gameCells array of cells
     * @param point
     * @return
     */
    public static int initGrid(GameCell[][] gameCells, Point point) {

        int cellSize;

        int width = point.x;
        int height = point.y;

        cellSize = width > height ? height / gameCells.length : width / gameCells.length;

        int startX = (width - (cellSize * gameCells.length)) / 2;
        int startY = (height - (cellSize * gameCells.length)) / 2;


        Utils.logD(Utils.class, "gridEdge:" + cellSize + "startX" + startX + "startY" + startY);

        for (int i = 0; i < gameCells.length; i++) {
            int offset = startX;
            for (int j = 0; j < gameCells.length; j++) {

                Rect rect = new Rect(offset, startY, offset + cellSize, startY + cellSize);
                boolean isAlive = false;
                Utils.logD(Utils.class, "rect:" + startX + "," + startY + "," + (startX + cellSize) + "," + (startY + cellSize) + " alive:" + isAlive);
                offset += cellSize;

                gameCells[i][j] = new GameCell(rect, isAlive);
            }
            startY += cellSize;
        }

        return cellSize;
    }


    /**
     * method to identify which cell was touched and set it as alive
     *
     * @param x x coordinate of touch
     * @param y y coordinate of touch
     */
    public static void selectCell(GameCell[][] gameCells, float x, float y) {

        Utils.logD(Utils.class, "CLICK:" + x + "," + y);
        Utils.logD(Utils.class, "top:" + gameCells[0][0].getRect().top);
        Utils.logD(Utils.class, "bottom:" + gameCells[19][0].getRect().bottom);
        Utils.logD(Utils.class, "left:" + gameCells[0][0].getRect().left);
        Utils.logD(Utils.class, "right:" + gameCells[0][19].getRect().right);

        if (y >= gameCells[0][0].getRect().top && y <= gameCells[19][0].getRect().bottom && x >= gameCells[0][0].getRect().left && x <= gameCells[0][19].getRect().right) {
            int i = 0;
            while (y > gameCells[i][0].getRect().bottom) {
                i++;
            }
            int j = 0;
            while (x > gameCells[0][j].getRect().right) {
                j++;
            }

            Utils.logD(Utils.class, "cell selected:" + i + "," + j);
            gameCells[i][j].setIsAlive(!gameCells[i][j].isAlive());
        }
    }
}
