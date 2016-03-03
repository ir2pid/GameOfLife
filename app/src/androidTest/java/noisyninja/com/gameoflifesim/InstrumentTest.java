package noisyninja.com.gameoflifesim;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrument test class
 * Created by ir2pid on 03/03/16.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class InstrumentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void changeText_sameActivity() {//Select one cell, press the button and match button text.

        onView(withId(R.id.gamegrid1)).perform(click());
        onView(withId(R.id.button))
                .perform(click()).check(matches(withText(R.string.stop_toggle)));

    }
}
