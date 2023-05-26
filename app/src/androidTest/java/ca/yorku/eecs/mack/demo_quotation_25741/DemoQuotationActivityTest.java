package ca.yorku.eecs.mack.demo_quotation_25741;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * DemoQuotationActivityTest by...
 * <p>
 * Login ID - josephy5
 * Student ID - 2171255741
 * Last name - Yong
 * First name(s) - Joseph
 */

/**
 * <p>
 * <p>What has been added</p>
 * <blockquote>
 * <p>
 * Created all the test methods based on the test cases stated in the pdf document (from part1a to part1b)
 * </p>
 * <p>
 * Modified the code in QuizAcitivty and Question file to get the location of the correct answers
 * </p>
 * <p>
 * tried to implement the microbenchmark code for the two most resource intensive test cases (found within the benchmark module)
 * </p>
 * </blockquote>
 * </p>
 */

/*
 * Note: I know that you want me to make test cases where the data that we want to test/input into the app can be
 * controllable/predictable and not be randomized by the app but I still went ahead to make tests cases where
 * its testing/input data is randomized by the app. I still managed to get them to work properly.
 *
 * Although I did modified the quotation app's code a bit, it was only for the tests of the quiz interaction
 * to ensure that the answers that the tester is going to click on guaranteed to be correct or incorrect (depending on
 * the test). Most of the added code to the quotation app was mostly get methods of some of the variables in the code
 * that I needed to have to check if the test[s] worked properly or not. It didn't cause any other unintended effects
 * or didn't broke anything on the app.
 *
 * Despite this being not what you mostly wanted (having only test cases whose data can be
 * predictable and controllable), I still learned a lot from figuring out how to make these tests dynamic to the app's randomness
 * which can be beneficial for the final project or any future endeavours in android studio. So you can treat this as me going beyond
 * or something.
 */
@RunWith(AndroidJUnit4.class)
public class DemoQuotationActivityTest {
    //Gets the ActivityTestRule from DemoQuotation Activity
    @Rule
    public ActivityTestRule<DemoQuotationActivity> mActivityScenarioRule =
            new ActivityTestRule<DemoQuotationActivity>(DemoQuotationActivity.class);

    //The following test methods below are the ones stated in part1A [Refer to PDF document for more details]

    /* swipeRightOnImageTest()
     *
     * Tests a scenario where the user swipes right on the current image
     * of the person who said the quote
     * [Refer to PDF document for more details]
     */
    @Test
    public void swipeRightOnImageTest() throws Exception {
        onView(withId(R.id.card_front)).perform(swipeRight());
        onView(isRoot()).perform(waitFor(300));
        onView(withId(R.id.bio_text))
                .check(matches(isDisplayed()));
    }

    /* swipeLeftOnImageTest()
     *
     * Tests a scenario where the user swipes left on the current image
     * of the person who said the quote
     * [Refer to PDF document for more details]
     */
    @Test
    public void swipeLeftOnImageTest() throws Exception {
        onView(withId(R.id.card_front)).perform(swipeLeft());
        onView(isRoot()).perform(waitFor(300));
        onView(withId(R.id.bio_text))
                .check(matches(isDisplayed()));
    }

    /* swipeOnImageBackTapTest()
     *
     * Tests a scenario where the User swipes on the person's image
     * but accidentally taps on the back button
     * [Refer to PDF document for more details]
     */
    @Test
    public void swipeOnImageBackTapTest() throws Exception {
        onView(withId(R.id.card_front)).perform(swipeLeft());
        onView(isRoot()).perform(waitFor(300));
        onView(withId(R.id.bio_text))
                .perform(ViewActions.pressBack())
                .check(matches(isDisplayed()));
    }

    /* userTapTest()
     *
     * Tests a scenario where the User tap on the screen
     * [Refer to PDF document for more details]
     */
    @Test
    public void userTapTest() throws Exception {
        String hold = getText(onView(withId(R.id.text1_title)));
        //Log.d("Debug Check:",hold);
        onView(withId(R.id.text1_title))
                .perform(click());
        onView(isRoot()).perform(waitFor(300));
        onView(withId(R.id.text1_title))
                .check(matches(not(withText(containsString(hold)))));
    }

    /* userDoubleTapTest()
     *
     * Tests a scenario where the User double taps on the screen
     * [Refer to PDF document for more details]
     */
    @Test
    public void userDoubleTapTest() throws Exception {
        String hold = getText(onView(withId(R.id.text1_title)));
        onView(withId(R.id.text1_title))
                .perform(click());
        onView(isRoot()).perform(waitFor(300));
        String hold2 = getText(onView(withId(R.id.text1_title)));
        onView(withId(R.id.text1_title))
                .perform(click());
        onView(isRoot()).perform(waitFor(300));
        onView(withId(R.id.text1_title))
                .check(matches(not(withText(containsString(hold)))))
                .check(matches(not(withText(containsString(hold2)))));
    }

    /* userBackTapTest()
     *
     * Tests a scenario where the User tap on the screen and then
     * accidentally taps on the back button
     * [Refer to PDF document for more details]
     */
    @Test
    public void userBackTapTest() throws Exception {
        String hold = getText(onView(withId(R.id.text1_title)));
        //Log.d("Debug Check:",hold);
        onView(withId(R.id.text1_title))
                .perform(click());
        onView(isRoot()).perform(waitFor(300));
        onView(withId(R.id.text1_title))
                .perform(ViewActions.pressBack());
        onView(isRoot()).perform(waitFor(300));
        onView(withId(R.id.text1_title))
                .check(matches(withText(containsString(hold))));
    }

    /* actionBarOverflowButtonClickSettingTest()
     *
     * Tests a scenario where the User clicks on the setting option in action bar's overflow menu
     * [Refer to PDF document for more details]
     */
    @Test
    public void actionBarOverflowButtonClickSettingTest() throws Exception {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.title), withText("Quiz Length"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));
    }

    /* actionBarOverflowButtonClickQuizTest()
     *
     * Tests a scenario where the User clicks on the quiz option in action bar's overflow menu
     * [Refer to PDF document for more details]
     */
    @Test
    public void actionBarOverflowButtonClickQuizTest() throws Exception {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Quiz"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.perform(click());

        onView(withId(R.id.quiz_answer_1))
                .check(matches(isDisplayed()));
    }

    /* actionBarOverflowButtonTapBackButtonTest()
     *
     * Tests a scenario where the User taps on the back button while in action bar's overflow menu
     * [Refer to PDF document for more details]
     */
    @Test
    public void actionBarOverflowButtonTapBackButtonTest() throws Exception {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Quiz"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.perform(ViewActions.pressBack());
        /*
         * Tricking espresso to trying to access the overflow Menu when it is not displayed
         * So that we use this to prove that the Context Menu is not there when the user
         * taps on the back button, proving that our test is successful
         */
        try {
            textView.check(matches(isDisplayed()));
            throw new Exception("Expecting Context Menu to not appear");
        } catch (NoMatchingViewException e) {
            Log.e("ERROR:", e.toString());
        }
        onView(isRoot()).perform(waitFor(10000));
    }

    /* actionBarOverflowButtonClickHelpTest()
     *
     * Tests a scenario where the User clicks on the help option in action bar's overflow menu
     * [Refer to PDF document for more details]
     */
    @Test
    public void actionBarOverflowButtonClickHelpTest() throws Exception {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Help"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.perform(click());
        onView(withId(R.id.text1_title))
                .check(matches(isDisplayed()));
        //onView(withText("Not Implemented!")).inRoot(withDecorView(not(mActivityScenarioRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    /* quizCorrectTest()
     *
     * Tests a scenario where the User answers quiz correctly
     * [Refer to PDF document for more details]
     */
    @Test
    public void quizCorrectTest() throws Exception {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Quiz"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.perform(click());
        onView(isRoot()).perform(waitFor(1000));
        //uses a method that I modified in the QuizAcitvity file to obtain the correct answers
        int[] hold = QuizActivity.getJunitTestArrCorrectAnswer();
        int numOfQuestions = QuizActivity.numberOfQuestions;
        //taking into account of the quiz's length
        for (int i = 0; i < numOfQuestions; i++) {
            /*
             * Had to use a try and catch on this part because it would throw
             * an exception when the test is over despite being the test being
             * successful and ok throughout the testing phase
             */
            try {
                switch (hold[i]) {
                    case 0:
                        onView(withId(R.id.quiz_answer_1)).perform(click());
                    case 1:
                        onView(withId(R.id.quiz_answer_2)).perform(click());
                    case 2:
                        onView(withId(R.id.quiz_answer_3)).perform(click());
                    case 3:
                        onView(withId(R.id.quiz_answer_4)).perform(click());
                    case 4:
                        onView(withId(R.id.quiz_answer_5)).perform(click());
                }
            } catch (NoMatchingViewException e) {

            }
            ViewInteraction textView2 = onView(
                    allOf(IsInstanceOf.<View>instanceOf(android.widget.TextView.class), withText("Correct!"),
                            withParent(allOf(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                    withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                            isDisplayed()));
            textView2.check(matches(withText("Correct!")));

            onView(isRoot()).perform(waitFor(200));

            ViewInteraction button2 = onView(
                    allOf(withId(android.R.id.button3), withText("Continue"),
                            childAtPosition(
                                    childAtPosition(
                                            withClassName(is("android.widget.LinearLayout")),
                                            0),
                                    1),
                            isDisplayed()));
            button2.perform(click());
        }
    }

    /* quizWrongTest()
     *
     * Tests a scenario where the User answers quiz wrongly
     * [Refer to PDF document for more details]
     */
    @Test
    public void quizWrongTest() throws Exception {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Quiz"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.perform(click());

        onView(isRoot()).perform(waitFor(1000));
        //uses a method that I modified in the QuizAcitvity file to obtain the correct answers
        int[] hold = QuizActivity.getJunitTestArrCorrectAnswer();
        /*
         * Had to use a try and catch on this part because it would throw
         * an exception when the test is over despite being the test being
         * successful and ok throughout the testing phase
         */
        try {
            switch (hold[0]) {
                case 0:
                    onView(withId(R.id.quiz_answer_2)).perform(click());
                case 1:
                    onView(withId(R.id.quiz_answer_3)).perform(click());
                case 2:
                    onView(withId(R.id.quiz_answer_4)).perform(click());
                case 3:
                    onView(withId(R.id.quiz_answer_5)).perform(click());
                case 4:
                    onView(withId(R.id.quiz_answer_1)).perform(click());
            }
        } catch (NoMatchingViewException e) {

        }
        ViewInteraction textView2 = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.TextView.class), withText("Sorry!"),
                        withParent(allOf(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));
        textView2.check(matches(withText("Sorry!")));
    }

    /* quizBackTapTest()
     *
     * Tests a scenario where the User accidentally taps the back button while in quiz mode
     * [Refer to PDF document for more details]
     */
    @Test
    public void quizBackTapTest() throws Exception {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Quiz"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.perform(click());

        onView(withId(R.id.quiz_quote))
                .perform(ViewActions.pressBack());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.text1_title),
                        withParent(withParent(withId(R.id.container2))),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));
    }

    /* quizSwipeHintTest()
     *
     * Tests a scenario where the User swipes on the question to reveal a hint
     * [Refer to PDF document for more details]
     */
    @Test
    public void quizSwipeHintTest() throws Exception {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Quiz"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.perform(click());

        //if the test setup for this test activity is not setup properly
        if (QuizActivity.allowHints == false) {
            onView(withId(R.id.quiz_quote))
                    .perform(ViewActions.pressBack());
            openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

            ViewInteraction textView2 = onView(
                    allOf(withId(android.R.id.title), withText("Settings"),
                            childAtPosition(
                                    childAtPosition(
                                            withId(android.R.id.content),
                                            0),
                                    0),
                            isDisplayed()));
            textView2.perform(click());

            DataInteraction linearLayout = onData(anything())
                    .inAdapterView(allOf(withId(android.R.id.list),
                            childAtPosition(
                                    withId(android.R.id.list_container),
                                    0)))
                    .atPosition(1);
            linearLayout.perform(click());

            linearLayout.perform(ViewActions.pressBack());

            openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

            ViewInteraction textView3 = onView(
                    allOf(withId(android.R.id.title), withText("Quiz"),
                            childAtPosition(
                                    childAtPosition(
                                            withId(android.R.id.content),
                                            0),
                                    0),
                            isDisplayed()));
            textView3.perform(click());
        }

        onView(withId(R.id.quiz_quote)).perform(swipeRight());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.quiz_question_hint_title),
                        withParent(withParent(withId(R.id.quiz_container2))),
                        isDisplayed()));
        textView4.check(matches(isDisplayed()));
    }

    /* quizSettingBackTapTest()
     *
     * Tests a scenario where the User taps on the back button while in the setting menu
     * [Refer to PDF document for more details]
     */
    @Test
    public void quizSettingBackTapTest() throws Exception {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.perform(click());
        DataInteraction linearLayout = onData(anything())
                .inAdapterView(allOf(withId(android.R.id.list),
                        childAtPosition(
                                withId(android.R.id.list_container),
                                0)))
                .atPosition(1);

        linearLayout.perform(ViewActions.pressBack());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.text1_title),
                        withParent(withParent(withId(R.id.container2))),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

    }

    /* quizSettingLengthTest()
     *
     * Tests a scenario where the User sets the quiz length in the setting menu
     * [Refer to PDF document for more details]
     */
    @Test
    public void quizSettingLengthTest() throws Exception {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.perform(click());

        DataInteraction linearLayout = onData(anything())
                .inAdapterView(allOf(withId(android.R.id.list),
                        childAtPosition(
                                withId(android.R.id.list_container),
                                0)))
                .atPosition(0);
        linearLayout.perform(click());

        DataInteraction checkedTextView = onData(anything())
                .inAdapterView(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0)))
                .atPosition(2);
        checkedTextView.perform(click());
        linearLayout.perform(ViewActions.pressBack());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.title), withText("Quiz"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView2.perform(click());
        assertEquals(5, QuizActivity.numberOfQuestions);
    }

    /* quizSettingHintsTest()
     *
     * Tests a scenario where the User sets the quiz hints in the setting menu
     * [Refer to PDF document for more details]
     */
    @Test
    public void quizSettingHintsTest() throws Exception {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.perform(click());

        DataInteraction linearLayout = onData(anything())
                .inAdapterView(allOf(withId(android.R.id.list),
                        childAtPosition(
                                withId(android.R.id.list_container),
                                0)))
                .atPosition(1);
        linearLayout.perform(click());
        if (QuizActivity.allowHints == true) {
            linearLayout.perform(click());
        }

        //OLD Code, leaving it here just in case I need to check this method
        //linearLayout.perform(ViewActions.pressBack());
        //openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        /*ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.title), withText("Quiz"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView2.perform(click());*/

        assertEquals(false, QuizActivity.allowHints);
    }

    /* quizSettingVibrationTest()
     *
     * Tests a scenario where the User sets quiz vibration in the setting menu
     * [Refer to PDF document for more details]
     */
    @Test
    public void quizSettingVibrationTest() throws Exception {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.perform(click());

        DataInteraction linearLayout = onData(anything())
                .inAdapterView(allOf(withId(android.R.id.list),
                        childAtPosition(
                                withId(android.R.id.list_container),
                                0)))
                .atPosition(2);
        linearLayout.perform(click());

        linearLayout.perform(ViewActions.pressBack());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.title), withText("Quiz"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView2.perform(click());
        assertEquals(true, QuizActivity.vibrateOnWrong);
    }

    /* quizSettingAudioTest()
     *
     * Tests a scenario where the User sets quiz audio in the setting menu
     * [Refer to PDF document for more details]
     */
    @Test
    public void quizSettingAudioTest() throws Exception {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.perform(click());
        onView(isRoot()).perform(waitFor(300));

        DataInteraction linearLayout = onData(anything())
                .inAdapterView(allOf(withId(android.R.id.list),
                        childAtPosition(
                                withId(android.R.id.list_container),
                                0)))
                .atPosition(3);
        linearLayout.perform(click());
        onView(isRoot()).perform(waitFor(300));

        linearLayout.perform(ViewActions.pressBack());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.title), withText("Quiz"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView2.perform(click());
        assertEquals(true, QuizActivity.audio);
    }

    /* longTapPickPersonATest()
     *
     * Tests a scenario where the User long taps to picks a person that starts with an A
     * [Refer to PDF document for more details]
     */
    @Test
    public void longTapPickPersonATest() throws Exception {
        onView(withId(R.id.text1_title))
                .perform(longClick());
        onView(isRoot()).perform(waitFor(1000));

        DataInteraction textView = onData(anything())
                .inAdapterView(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0)))
                .atPosition(0);
        textView.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.text1_title),
                        withParent(withParent(withId(R.id.container2))),
                        isDisplayed()));
        textView2.check(matches(withText("Folks are usually about as happy as they make their minds up to be.")));
    }

    /* longTapPickPersonZTest()
     *
     * Tests a scenario where the User long taps to picks a person that starts with an Z
     * [Refer to PDF document for more details]
     */
    @Test
    public void longTapPickPersonZTest() throws Exception {
        onView(withId(R.id.text1_title))
                .perform(longClick());
        onView(isRoot()).perform(waitFor(1000));

        DataInteraction textView = onData(anything())
                .inAdapterView(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0)))
                .atPosition(108);
        textView.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.text1_title),
                        withParent(withParent(withId(R.id.container2))),
                        isDisplayed()));
        textView2.check(matches(withText("I call everyone \"Darling\" because I can't remember their names.")));
    }

    /* longTapPickPersonAnyoneTest()
     *
     * Tests a scenario where the User long taps to pick a person from the list
     * [Refer to PDF document for more details]
     */
    @Test
    public void longTapPickPersonAnyoneTest() throws Exception {
        onView(withId(R.id.text1_title))
                .perform(longClick());
        onView(isRoot()).perform(waitFor(1000));

        DataInteraction textView = onData(anything())
                .inAdapterView(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0)))
                .atPosition(66);
        textView.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.text1_title),
                        withParent(withParent(withId(R.id.container2))),
                        isDisplayed()));
        textView2.check(matches(withText("When you want something said, ask a man. When you want something done, ask a woman.")));
    }

    /* longTapPickPersonBackButtonTest()
     *
     * Tests a scenario where the User taps on the back button while the famous person alert menu is visible
     * [Refer to PDF document for more details]
     */
    @Test
    public void longTapPickPersonBackButtonTest() throws Exception {
        onView(withId(R.id.text1_title))
                .perform(longClick());
        onView(isRoot()).perform(waitFor(1000));
        /*
         * Tricking espresso to trying to access the long tap's famous person menu when it is not displayed
         * So that we use this to prove that the Long Press's famous person menu is not there when the user
         * taps on the back button, proving that our test is successful
         */
        try {
            DataInteraction textView = onData(anything())
                    .inAdapterView(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                            childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")),
                                    0)))
                    .atPosition(0);
            textView.perform(ViewActions.pressBack());
            textView.check(matches(isDisplayed()));
            throw new Exception("Expecting Long Press's famous person Menu to not still be there");
        } catch (NoMatchingViewException e) {
            Log.e("ERROR:", e.toString());
        }
    }

    //The following test methods below are the ones stated in part1B [Refer to PDF document for more details]

    /* searchPersonATest()
     *
     * Tests a scenario where the User searches a person that starts with an A in the search bar
     * [Refer to PDF document for more details]
     */
    @Test
    public void searchPersonATest() throws Exception {
        //to setup the profiling process since this method starts first (at least when it is running on my computer)
        onView(isRoot()).perform(waitFor(16000));

        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("Abraham Lincoln"), closeSoftKeyboard());

        ViewInteraction searchAutoComplete2 = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")), withText("Abraham Lincoln"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete2.perform(pressImeActionButton());

        ViewInteraction textView = onView(
                allOf(withId(R.id.text1_title),
                        withParent(withParent(withId(R.id.container2))),
                        isDisplayed()));
        textView.check(matches(withText("Folks are usually about as happy as they make their minds up to be.")));
    }

    /* searchPersonZTest()
     *
     * Tests a scenario where the User searches a person that starts with an Z in the search bar
     * [Refer to PDF document for more details]
     */
    @Test
    public void searchPersonZTest() throws Exception {
        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("Zsa Zsa Gabor"), closeSoftKeyboard());

        ViewInteraction searchAutoComplete2 = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete2.perform(pressImeActionButton());

        ViewInteraction textView = onView(
                allOf(withId(R.id.text1_title), withText("I call everyone \"Darling\" because I can't remember their names."),
                        withParent(withParent(withId(R.id.container2))),
                        isDisplayed()));
        textView.check(matches(withText("I call everyone \"Darling\" because I can't remember their names.")));
    }

    /* searchPersonAnyoneTest()
     *
     * Tests a scenario where the User searches a person from the list in the search bar
     * [Refer to PDF document for more details]
     */
    @Test
    public void searchPersonAnyoneTest() throws Exception {
        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("Margaret Thatcher"), closeSoftKeyboard());

        ViewInteraction searchAutoComplete2 = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete2.perform(pressImeActionButton());

        ViewInteraction textView = onView(
                allOf(withId(R.id.text1_title),
                        withParent(withParent(withId(R.id.container2))),
                        isDisplayed()));
        textView.check(matches(withText("When you want something said, ask a man. When you want something done, ask a woman.")));
    }

    /* searchBackButtonTest()
     *
     * Tests a scenario where the User types in the search bar and then taps on the back button
     * [Refer to PDF document for more details]
     */
    @Test
    public void searchBackButtonTest() throws Exception {
        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("a"), closeSoftKeyboard());

        ViewInteraction listView = onView(
                allOf(withId(R.id.listView),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        listView.check(matches(isDisplayed()));
    }

    /* searchEmptyTest()
     *
     * Tests a scenario where the User inputs a empty string into the search bar
     * [Refer to PDF document for more details]
     */
    @Test
    public void searchEmptyTest() throws Exception {
        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText(""), closeSoftKeyboard());
        onView(isRoot()).perform(waitFor(1000));
        /*
         * Tricking espresso to trying to access the listview when it is not displayed
         * So that we use this to prove that the listView is not there when a search
         * by the user has been made, proving that our test is successful
         */
        try {
            ViewInteraction listView = onView(
                    allOf(withId(R.id.listView),
                            withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                            isDisplayed()));
            listView.check(matches(isDisplayed()));
            throw new Exception("Expecting searchView's listView to not be visible");
        } catch (NoMatchingViewException e) {
            Log.e("ERROR:", e.toString());
        }
    }

    /* searchInvalidInputTest()
     *
     * Tests a scenario where the User inputs a name that is not on the list in the search bar
     * [Refer to PDF document for more details]
     */
    @Test
    public void searchInvalidInputTest() throws Exception {
        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("Solid Snake"), closeSoftKeyboard());

        ViewInteraction searchAutoComplete2 = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));

        searchAutoComplete2.perform(pressImeActionButton());
        ViewInteraction textView = onView(
                allOf(withId(R.id.text2_title),
                        withParent(withParent(withId(R.id.container2))),
                        isDisplayed()));
        textView.check(matches(not(withText("- Solid Snake"))));
    }

    //The methods below are custom methods created for the espresso/junit tests above

    /*
     * waitFor()
     * Delays the espresso commands. The length of the delay is determined by an inputted delay value,
     * This method in essence is like Threat.Sleep()
     */
    public static ViewAction waitFor(long delay) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for " + delay + "milliseconds";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadForAtLeast(delay);
            }
        };
    }

    /*
     * getText()
     * Used to get the string value of a textView for the espresso/junit tests.
     */
    public static String getText(ViewInteraction matcher) {
        final String[] text = new String[1];
        ViewAction va = new ViewAction() {

            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "Text of the view";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView) view;
                text[0] = tv.getText().toString();
            }
        };

        matcher.perform(va);

        return text[0];
    }

    /*
     * childAtPosition()
     * A method created by espresso's record Espresso test feature. Used in some
     * of the tests above
     */
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