package noisyninja.com.gameoflifesim.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Class to draw on canvas game information
 * Created by ir2pid on 29/02/16.
 */
public class GameCanvas extends View {

    Context context;
    private Paint mPaintAlive;
    private Paint mPaintDead;
    protected float mX, mY;
    protected GameCell[][] gameCells;
    protected boolean[][] gameCellsTemp;
    protected int cellSize;

    public GameCanvas(Context context) {
        super(context);
    }

    public GameCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        gameCells = new GameCell[20][20];
        gameCellsTemp = new boolean[20][20];

        //Paint with alive attributes
        mPaintAlive = new Paint();
        mPaintAlive.setAntiAlias(true);
        mPaintAlive.setColor(Color.BLACK);
        mPaintAlive.setStyle(Paint.Style.FILL);
        mPaintAlive.setStrokeJoin(Paint.Join.ROUND);
        mPaintAlive.setStrokeWidth(4f);


        //Paint with dead attributes
        mPaintDead = new Paint();
        mPaintDead.setAntiAlias(true);
        mPaintDead.setColor(Color.BLACK);
        mPaintDead.setStyle(Paint.Style.STROKE);
        mPaintDead.setStrokeJoin(Paint.Join.ROUND);
        mPaintDead.setStrokeWidth(4f);

    }

    public GameCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameCanvas(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * override ondraw to draw game cells
     * @param canvas canvas to draw on
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(int i = 0; i< gameCells.length; i++){
            for (int j = 0; j < gameCells[i].length; j++) {
                Paint paint = gameCells[i][j].isAlive()?mPaintAlive:mPaintDead;
                canvas.drawRect(gameCells[i][j].getRect(), paint);
            }
        }

    }

}
