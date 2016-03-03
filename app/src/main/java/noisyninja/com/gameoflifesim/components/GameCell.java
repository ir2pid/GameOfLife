package noisyninja.com.gameoflifesim.components;

import android.graphics.Rect;

/**
 * Created by ir2pid on 01/03/16.
 */
public class GameCell {

    private Rect rect;
    private boolean isAlive;

    public GameCell(Rect rect) {
        this.rect = rect;
        this.isAlive = false;
    }

    public GameCell(Rect rect, boolean isAlive) {
        this.rect = rect;
        this.isAlive = isAlive;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
}
