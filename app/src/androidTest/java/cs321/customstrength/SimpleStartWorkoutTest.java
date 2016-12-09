package cs321.customstrength;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class SimpleStartWorkoutTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void simpleStartWorkoutTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.programsBtn), withText("My Programs"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction button = onView(
                withText("+"));
        button.perform(scrollTo(), click());

        ViewInteraction appCompatEditText = onView(
                withId(R.id.programName));
        appCompatEditText.perform(scrollTo(), replaceText("test"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.dayPlusButton), withText("+")));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.weekPlusButton), withText("+")));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction editText = onView(
                withHint("Name of Week"));
                //withClassName(is("android.widget.EditText")));
        editText.perform(scrollTo(), replaceText("beginning"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(withHint("Name of Day"), isDisplayed()));
                //allOf(withClassName(is("android.widget.EditText")), isDisplayed()));
        editText2.perform(replaceText("arms"), closeSoftKeyboard());

        ViewInteraction button2 = onView(
                allOf(withText("Add Exercise"), isDisplayed()));
        button2.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                withId(R.id.numberPicker));
        appCompatEditText2.perform(scrollTo(), replaceText("5"), closeSoftKeyboard());

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.search), withContentDescription("Hint Search"), isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withId(R.id.search_src_text),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("arm"), closeSoftKeyboard());

        ViewInteraction searchAutoComplete2 = onView(
                allOf(withId(R.id.search_src_text), withText("arm"),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete2.perform(pressImeActionButton());

        ViewInteraction button3 = onView(
                withText("ARM CIRCLES"));
        button3.perform(scrollTo(), click());

        ViewInteraction editText3 = onView(
                allOf(withClassName(is("android.widget.EditText")),
                        withParent(withId(R.id.volumeAndIntensity))));
        editText3.perform(scrollTo(), replaceText("5"), closeSoftKeyboard());

        ViewInteraction button4 = onView(
                allOf(withText("Save"),
                        withParent(withId(R.id.volumeAndIntensity))));
        button4.perform(scrollTo(), click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withText("Save"), isDisplayed()));
        appCompatButton4.perform(click());

        pressBack();

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.startWorkoutBtn), withText("Start Workout"), isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction button5 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.activity_validate_workout),
                                1),
                        0),
                        isDisplayed()));
        button5.check(matches(isDisplayed()));

        ViewInteraction button6 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.activity_validate_workout),
                                1),
                        1),
                        isDisplayed()));
        button6.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withText("Current Workout: test, Week: beginning, Day: arms"),
                        childAtPosition(
                                allOf(withId(R.id.currentProgram),
                                        childAtPosition(
                                                withId(R.id.activity_validate_workout),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Current Workout: test, Week: beginning, Day: arms")));

        ViewInteraction appCompatButton6 = onView(
                allOf(withText("Start Workout"), isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withText("ARM CIRCLES"),
                        childAtPosition(
                                allOf(withId(R.id.currentProgramWeeksList),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                1)),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("ARM CIRCLES")));

        ViewInteraction appCompatTextView = onView(
                allOf(withText("ARM CIRCLES"),
                        childAtPosition(
                                withId(R.id.currentProgramWeeksList),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        pressBack();

        ViewInteraction appCompatButton7 = onView(
                allOf(withText("Select new workout"), isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction button7 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.mainLayout),
                                childAtPosition(
                                        withId(R.id.activity_select_workout),
                                        0)),
                        0),
                        isDisplayed()));
        button7.check(matches(isDisplayed()));

        ViewInteraction button8 = onView(
                allOf(withText("test"),
                        withParent(allOf(withId(R.id.mainLayout),
                                withParent(withId(R.id.activity_select_workout))))));
        button8.perform(scrollTo(), click());

        ViewInteraction button9 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.mainLayout),
                                childAtPosition(
                                        withId(R.id.activity_select_workout),
                                        0)),
                        0),
                        isDisplayed()));
        button9.check(matches(isDisplayed()));

        ViewInteraction button10 = onView(
                allOf(withText("beginning"),
                        withParent(allOf(withId(R.id.mainLayout),
                                withParent(withId(R.id.activity_select_workout))))));
        button10.perform(scrollTo(), click());

        ViewInteraction button11 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.mainLayout),
                                childAtPosition(
                                        withId(R.id.activity_select_workout),
                                        0)),
                        0),
                        isDisplayed()));
        button11.check(matches(isDisplayed()));

        ViewInteraction button12 = onView(
                allOf(withText("arms"),
                        withParent(allOf(withId(R.id.mainLayout),
                                withParent(withId(R.id.activity_select_workout))))));
        button12.perform(scrollTo(), click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
