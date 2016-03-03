package noisyninja.com.gameoflifesim;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import noisyninja.com.gameoflifesim.components.GameLogic;
import noisyninja.com.gameoflifesim.components.GameEngine;

public class MainActivity extends Activity implements View.OnClickListener {

    GameEngine gameEngine;
    long startTime = 0;
    Button startButton;
    TextView fpsTextView;

    // Define the Handler that receives messages from the thread and update the progress
    private final Handler timerHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameLogic gameGrid = (GameLogic) findViewById(R.id.gamegrid1);
        fpsTextView = (TextView) findViewById(R.id.textView);
        startButton = (Button) findViewById(R.id.button);
        startButton.setOnClickListener(this);
        init(gameGrid);
    }

    /**
     * initializes game engine
     *
     * @param gameLogic contains game logic and canvas
     */
    private void init(GameLogic gameLogic) {
        startButton.setText(Utils.getResString(getBaseContext(), R.string.start_toggle));
        gameEngine = new GameEngine(gameLogic);
    }

    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (gameEngine.getGameLogic().isRunning()) {
            timerHandler.postDelayed(timerRunnable, 1000 / GameEngine.FPS);
        }
        fpsTextView.setText(Utils.getResString(getBaseContext(), R.string.start_instruction));
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button) {
            if (gameEngine.getGameLogic().isRunning()) {//stop game if running
                gameEngine.getGameLogic().setIsRunning(false);
                timerHandler.removeCallbacks(timerRunnable);
                startButton.setText(Utils.getResString(getBaseContext(), R.string.start_toggle));
                fpsTextView.setText(Utils.getResString(getBaseContext(), R.string.start_instruction));
            } else {//start game if stopped
                gameEngine.getGameLogic().setIsRunning(true);
                startTime = System.currentTimeMillis();
                timerHandler.postDelayed(timerRunnable, 1000 / GameEngine.FPS);
                startButton.setText(Utils.getResString(getBaseContext(), R.string.stop_toggle));
            }
        }
    }

    /**
     * runnable to update game engine every 1 sec
     */
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            fpsTextView.setText(String.format("Generation lapse- %d:%02d", minutes, seconds));
            gameEngine.update();
            gameEngine.refresh();
            timerHandler.postDelayed(this, 1000 / GameEngine.FPS);

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
