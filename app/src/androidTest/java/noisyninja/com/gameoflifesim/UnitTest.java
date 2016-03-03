package noisyninja.com.gameoflifesim;

import android.graphics.Point;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

import noisyninja.com.gameoflifesim.components.GameCell;
import noisyninja.com.gameoflifesim.components.GameGrid;
import noisyninja.com.gameoflifesim.components.GameUtils;

/**
 * Unit test class
 * Created by ir2pid on 03/03/16.
 */
public class UnitTest {

    Point point;
    GameCell[][] gameCells;

    @Before
    public void setUp() {
        point = new Point(1024, 768);
        gameCells = new GameCell[20][20];
    }

    @Test
    public void checkCellSizeTest() {
        assertThat(GameUtils.initGrid(gameCells, point), is(not(0)));
    }
}
