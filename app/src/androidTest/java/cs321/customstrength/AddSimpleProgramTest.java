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
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
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
public class AddSimpleProgramTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addSimpleProgramTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.programsBtn), withText("My Programs"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Nothing here... try adding a program"),
                        childAtPosition(
                                allOf(withId(R.id.programLayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Nothing here... try adding a program")));

        ViewInteraction button = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.programLayout),
                                0),
                        2),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.programLayout),
                                0),
                        1),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                withText("+"));
        button3.perform(scrollTo(), click());

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
        editText.perform(scrollTo(), replaceText("beginning"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(withHint("Name of Day"), isDisplayed()));
        editText2.perform(replaceText("arms"), closeSoftKeyboard());

        ViewInteraction button4 = onView(
                allOf(withText("Add Exercise"), isDisplayed()));
        button4.perform(click());

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

        ViewInteraction button5 = onView(
                withText("ARM CIRCLES"));
        button5.perform(scrollTo(), click());

        ViewInteraction editText3 = onView(
                allOf(withClassName(is("android.widget.EditText")),
                        withParent(withId(R.id.volumeAndIntensity))));
        editText3.perform(scrollTo(), replaceText("3"), closeSoftKeyboard());

        ViewInteraction button6 = onView(
                allOf(withText("Save"),
                        withParent(withId(R.id.volumeAndIntensity))));
        button6.perform(scrollTo(), click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withText("Save"), isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction button7 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.programLayout),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0)),
                        1),
                        isDisplayed()));
        button7.check(matches(isDisplayed()));

        ViewInteraction button8 = onView(
                allOf(withText("test"),
                        withParent(withId(R.id.programLayout))));
        button8.perform(scrollTo(), click());

        ViewInteraction button9 = onView(
                allOf(withText("test:\nbeginning:\narms:\n1. ARM CIRCLES\n\n"),
                        withParent(withId(R.id.programLayout))));
        button9.perform(scrollTo(), click());

        ViewInteraction button10 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.programLayout),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0)),
                        1),
                        isDisplayed()));

        ViewInteraction button11 = onView(
                withText("-"));
        button11.perform(scrollTo(), click());

        ViewInteraction button12 = onView(
                allOf(withText("test"),
                        withParent(withId(R.id.programLayout))));
        button12.perform(scrollTo(), click());

        button10.check(doesNotExist());
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
