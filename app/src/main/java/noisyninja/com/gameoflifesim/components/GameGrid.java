package noisyninja.com.gameoflifesim.components;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;

import noisyninja.com.gameoflifesim.Utils;

/**
 * Class to draw 20x20 grid
 * Created by ir2pid on 01/03/16.
 */
public class GameGrid extends GameCanvas {

    public GameGrid(Context context) {
        super(context);
    }

    public GameGrid(Context context, AttributeSet attrs) {
        super(context, attrs);

        Point point = Utils.getScreenDimention(context);
        cellSize = GameUtils.initGrid(gameCells, point);
    }

    public GameGrid(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameGrid(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void update() {

    }
}
