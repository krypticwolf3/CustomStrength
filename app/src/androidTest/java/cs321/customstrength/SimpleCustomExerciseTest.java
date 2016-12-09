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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class SimpleCustomExerciseTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void simpleCustomExerciseTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.exerciseBtn), withText("Exercises"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withText("Add Custom"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatEditText = onView(
                withId(R.id.customNameValue));
        appCompatEditText.perform(scrollTo(), replaceText("arm hat trick"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                withId(R.id.primaryValue));
        appCompatEditText2.perform(scrollTo(), replaceText("bicep"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                withId(R.id.secondaryValue));
        appCompatEditText3.perform(scrollTo(), replaceText("triceps"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                withId(R.id.equipmentValue));
        appCompatEditText4.perform(scrollTo(), replaceText("dumbell"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                withId(R.id.difficultyValue));
        appCompatEditText5.perform(scrollTo(), replaceText("tough"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.createButton), withText("Create")));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.cardTitle), withText("ARM HAT TRICK"),
                        childAtPosition(
                                allOf(withId(R.id.cardView),
                                        childAtPosition(
                                                withId(R.id.recyclerView),
                                                0)),
                                0),
                        isDisplayed()));
        //textView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.cardTitle), withText("ARM HAT TRICK"),
                        childAtPosition(
                                allOf(withId(R.id.cardView),
                                        childAtPosition(
                                                withId(R.id.recyclerView),
                                                0)),
                                0),
                        isDisplayed()));
        //textView2.check(matches(withText("ARM HAT TRICK")));

        ViewInteraction appCompatButton4 = onView(
                allOf(withText("Add Custom"), isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatSpinner = onView(
                withId(R.id.typeValue));
        appCompatSpinner.perform(scrollTo(), click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(android.R.id.text1), withText("Cardio"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatEditText6 = onView(
                withId(R.id.customNameValue));
        appCompatEditText6.perform(scrollTo(), click());

        ViewInteraction appCompatEditText7 = onView(
                withId(R.id.customNameValue));
        appCompatEditText7.perform(scrollTo(), replaceText("biz run"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                withId(R.id.primaryValue));
        appCompatEditText8.perform(scrollTo(), replaceText("calf"), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                withId(R.id.secondaryValue));
        appCompatEditText9.perform(scrollTo(), replaceText("shin"), closeSoftKeyboard());

        ViewInteraction appCompatEditText10 = onView(
                withId(R.id.equipmentValue));
        appCompatEditText10.perform(scrollTo(), replaceText("none"), closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
                withId(R.id.difficultyValue));
        appCompatEditText11.perform(scrollTo(), replaceText("tough"), closeSoftKeyboard());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.createButton), withText("Create")));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.cardTitle), withText("BIZ RUN"),
                        childAtPosition(
                                allOf(withId(R.id.cardView),
                                        childAtPosition(
                                                withId(R.id.recyclerView),
                                                1)),
                                0),
                        isDisplayed()));
        //textView3.check(matches(withText("BIZ RUN")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.cardTitle), withText("BIZ RUN"),
                        childAtPosition(
                                allOf(withId(R.id.cardView),
                                        childAtPosition(
                                                withId(R.id.recyclerView),
                                                1)),
                                0),
                        isDisplayed()));
        //textView4.check(matches(isDisplayed()));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.cardTitle), withText("BIZ RUN"),
                        childAtPosition(
                                allOf(withId(R.id.cardView),
                                        childAtPosition(
                                                withId(R.id.recyclerView),
                                                1)),
                                0),
                        isDisplayed()));
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
