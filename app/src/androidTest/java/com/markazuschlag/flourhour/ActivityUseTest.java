package com.markazuschlag.flourhour;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ActivityUseTest {
    private static final String TEST_TARGET = "15";
    private static final int TEST_COUNT = 15;
    private static final int TEST_INCREMENT_I = 2;

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void iterateIncrements() {
        String[] myArray =
                mActivityRule.getActivity().getResources()
                .getStringArray(R.array.pref_increments);
        int size = myArray.length;
        for (int i = 0; i < size; i++) {
            onView(withId(R.id.incrementSpinner)).perform(click());
            onData(is(myArray[i])).perform(click());
            onView(withId(R.id.startButton)).perform(click());
            onView(withId(R.id.descriptionTextView))
                    .check(matches(withText(containsString(myArray[i]))));
            Espresso.pressBack();
        }
    }
    @Test
    public void checkTarget() {
        onView(withId(R.id.targetEditText)).perform(replaceText(TEST_TARGET));
        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.countText)).check(matches(withText(TEST_TARGET)));
    }

    @Test
    public void runApp() {
        String[] myArray =
                mActivityRule.getActivity().getResources()
                .getStringArray(R.array.pref_increments);

        onView(withId(R.id.targetEditText)).perform(replaceText(TEST_TARGET));
        onView(withId(R.id.incrementSpinner)).perform(click());
        onData(is(myArray[TEST_INCREMENT_I])).perform(click());
        onView(withId(R.id.startButton)).perform(click());

        onView(withId(R.id.countText)).check(matches(withText(TEST_TARGET)));
        onView(withId(R.id.descriptionTextView))
                .check(matches(withText(containsString(myArray[TEST_INCREMENT_I]))));
        for (int i = 0; i < TEST_COUNT; i++) {
            onView(withId(R.id.countButton)).perform(click());
        }

        String finish = mActivityRule.getActivity().getResources()
                .getString(R.string.ready);
        onView(withId(R.id.finishTextView)).check(matches(withText(finish)));
    }
}
