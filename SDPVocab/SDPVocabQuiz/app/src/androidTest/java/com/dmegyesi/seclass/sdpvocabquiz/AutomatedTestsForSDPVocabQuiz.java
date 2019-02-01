package com.dmegyesi.seclass.sdpvocabquiz;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import java.util.Random;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AutomatedTestsForSDPVocabQuiz {

    @Rule
    public ActivityTestRule<LoginRegisterActivity> aActivityRule = new ActivityTestRule<>(
            LoginRegisterActivity.class);

    @Test
    public void aSmokeTest() {

        int randNum = generateRandomNum(1000);
        String username = "User" + randNum;
        String emailAddress = "User" + randNum + "@gmail.com";
        String quizName = "Quiz " + randNum;

        // Login: Failure.
        LoginNotExistingUser();

        // Register New User.
        RegisterNewUser(username, emailAddress);

        // Main Menu: Add quiz.
        LoginUserAddQuiz(username, quizName);

        // Main Menu: Practise quiz.
        int randNum2 = generateRandomNum(1000);
        String username2 = "User" + randNum2;
        String emailAddress2 = "User" + randNum2 + "@gmail.com";

        RegisterNewUser(username2, emailAddress2);
        LoginUserPractiseQuiz(username2, quizName);

        // Main Menu: View list of Quiz score statistics.
        LoginUserViewStats(username2, quizName);

        // Main Menu: Remove quiz.
        LoginUserRemoveQuiz(username, quizName);
    }

    /**
     * Private method that tests than a non-existing user cannot login.
     */
    private void LoginNotExistingUser() {

        onView(withId(R.id.welcomeBanner)).check(matches(withText("Welcome to SDPVocab")));
        onView(withId(R.id.loginUsername)).perform(clearText(), typeText("NoUser"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform((click()));
        onView(withText("Username doesn't exist. Register first")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    /**
     * Private method that tests to ensure that a user can successfully registered.
     *
     * @param username Username of 'to be' registered user.
     * @param emailAddress Email address of 'to be' registered user.
     */
    private void RegisterNewUser(String username, String emailAddress) {

        // Register new user.
        onView(withId(R.id.welcomeBanner)).check(matches(withText("Welcome to SDPVocab")));
        onView(withId(R.id.loginUsername)).perform(clearText(), typeText(username), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.registerButton)).perform((click()));

        onView(withId(R.id.registerNewUserBanner)).check(matches(withText("Register a new user")));
        onView(withId(R.id.loginUsername)).perform(clearText(), typeText(username), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.majorSubject)).perform(clearText(), typeText("Computer Science"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.sophomoreRadioButton)).perform((click()));
        onView(withId(R.id.emailAddress)).perform(clearText(), typeText(emailAddress), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.registerButton)).perform((click()));
    }

    /**
     * Private method that tests to ensure that a quiz can be successfully added.
     *
     * @param username Username of a registered user.
     * @param quizName Quiz name.
     */
    private void LoginUserAddQuiz(String username, String quizName) {

        // Login with newly created user.
        onView(withId(R.id.welcomeBanner)).check(matches(withText("Welcome to SDPVocab")));
        onView(withId(R.id.loginUsername)).perform(clearText(), typeText(username), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform((click()));

        // Main Menu: Add a quiz. Create a new quiz.
        onView(withId(R.id.quizMainMenuBanner)).check(matches(withText("Main Menu")));
        onView(withId(R.id.addQuizRadioButton)).perform(click());
        onView(withId(R.id.confirmButton)).perform(click());
        onView(withId(R.id.createNewQuizBanner)).check(matches(withText("Create Quiz")));
        onView(withId(R.id.quizName)).perform(clearText(), typeText(quizName), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.quizDescription)).perform(clearText(),
                typeText("This quiz is for automation purposes to show functionality. This quiz will contain 4 tests for students to practise."), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.numberQuizWords)).perform(clearText(), typeText("4"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.createQuizButton)).perform(click());

        onView(withId(R.id.addQuizWordBanner)).check(matches(withText("Add a word")));
        onView(withId(R.id.wordName)).perform(clearText(), typeText("Apricot"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.correctDefinition)).perform(clearText(),
                typeText("A fruit or the tree that bears the fruit, of several species of the genus Prunus."), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.incorrectDefinition1)).perform(clearText(),
                typeText("A ventricle of the human heart."), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.incorrectDefinition2)).perform(clearText(),
                typeText("The name of the famous tennis player."), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.incorrectDefinition3)).perform(clearText(),
                typeText("A part of an automobile engine."), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.addWordButton)).perform(click());

        onView(withId(R.id.addQuizWordBanner)).check(matches(withText("Add a word")));
        onView(withId(R.id.wordName)).perform(clearText(), typeText("Golf"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.correctDefinition)).perform(clearText(),
                typeText("A club and ball sport in which players use various clubs to hit balls into a holes on a course in as few strokes as possible."), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.incorrectDefinition1)).perform(clearText(),
                typeText("A type of gasket for an automotive engine that uses 8 cylinders or more."), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.incorrectDefinition2)).perform(clearText(),
                typeText("Name of a popular chess strategy made famous in 1951.  It is used to gain momentum over an opponent."), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.incorrectDefinition3)).perform(clearText(),
                typeText("A type of Log(N) algorithm used the study of computer science, and mathematics."), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.addWordButton)).perform(click());

        onView(withId(R.id.addQuizWordBanner)).check(matches(withText("Add a word")));
        onView(withId(R.id.wordName)).perform(clearText(), typeText("Coffee"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.correctDefinition)).perform(clearText(),
                typeText("A caffeine laden drink made from the roasted and ground bean like seeds of a tropical shrub, served hot or iced."), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.incorrectDefinition1)).perform(clearText(),
                typeText("A small red feathered bird found in the upper part of eastern Egypt. It is considered to be on the near extinct list."), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.incorrectDefinition2)).perform(clearText(),
                typeText("Name of a famous magician who died in 1943, as he fell victim to his own underwater bear trap illusion."), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.incorrectDefinition3)).perform(clearText(),
                typeText("A fossilized type of mincemeat found in some ancient Mexican temples. Believed to be supernatural in origin."), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.addWordButton)).perform(click());

        onView(withId(R.id.addQuizWordBanner)).check(matches(withText("Add a word")));
        onView(withId(R.id.wordName)).perform(clearText(), typeText("Computer"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.correctDefinition)).perform(clearText(),
                typeText("An electronic device for storing and processing binary data, according to instructions given to it in a variable program."), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.incorrectDefinition1)).perform(clearText(),
                typeText("A hand waving greeting used in many African cultures."), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.incorrectDefinition2)).perform(clearText(),
                typeText("Name of the American Football team that won the 2017 Super Bowl."), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.incorrectDefinition3)).perform(clearText(),
                typeText("A speciality type of yogurt and milkshake served in the summer months at Starbucks restaurants."), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.addWordButton)).perform(click());
        onView(withId(R.id.logoutButton)).perform(click());
    }

    /**
     * Private method that tests to ensure that a quiz can be successfully practised by another student.
     *
     * @param username Username of a registered user.
     * @param quizName Quiz name.
     */
    private void LoginUserPractiseQuiz(String username, String quizName) {

        // Login with newly created user.
        onView(withId(R.id.welcomeBanner)).check(matches(withText("Welcome to SDPVocab")));
        onView(withId(R.id.loginUsername)).perform(clearText(), typeText(username), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform((click()));

        // Main Menu: Practise a quiz created by another student.
        onView(withId(R.id.quizMainMenuBanner)).check(matches(withText("Main Menu")));
        onView(withId(R.id.practiseQuizRadioButton)).perform(click());
        onView(withId(R.id.confirmButton)).perform(click());

        onView(withId(R.id.SelectQuizBanner)).check(matches(withText("Select Quiz")));
        onView(withId(R.id.SelectQuizMultiSelect)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(quizName))).check(matches(withText(quizName)));
        onData(allOf(is(instanceOf(String.class)), is(quizName))).perform(click());
        onView(withId(R.id.takeQuizButton)).perform(click());

        // Practise the Quiz.
        onView(withId(R.id.practiseQuizBanner)).check(matches(withText("Practise Quiz")));

        // Q1 (select 1st answer).
        onView(withId(R.id.quizQuestion1)).perform(click());
        onView(withId(R.id.nextQuizButton)).perform(click());

        // Q2 (select 2nd answer).
        onView(withId(R.id.quizQuestion2)).perform(click());
        onView(withId(R.id.nextQuizButton)).perform(click());

        // Q3 (select 3rd answer).
        onView(withId(R.id.quizQuestion3)).perform(click());
        onView(withId(R.id.nextQuizButton)).perform(click());

        // Q4 (select 4th answer).
        onView(withId(R.id.quizQuestion4)).perform(click());
        onView(withId(R.id.nextQuizButton)).perform(click());

        // Review Quiz score.
        onView(withId(R.id.scoreForQuizBanner)).check(matches(withText("Score for Quiz")));
        onView(withId(R.id.studentUsername)).check(matches(withText(username)));
        onView(withId(R.id.quizScore)).check(matches(isDisplayed()));
        onView(withId(R.id.quizScore)).check(matches(not(withText(""))));
        onView(withId(R.id.returnMainMenuButton)).perform(click());
        onView(withId(R.id.logoutButton)).perform(click());
    }

    /**
     * Private method that tests to ensure that the statistics for both a student and a quiz can be successfully viewed.
     *
     * @param username Username of a registered user.
     * @param quizName Quiz name.
     */
    private void LoginUserViewStats(String username, String quizName) {

        // Login with newly created user.
        onView(withId(R.id.welcomeBanner)).check(matches(withText("Welcome to SDPVocab")));
        onView(withId(R.id.loginUsername)).perform(clearText(), typeText(username), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform((click()));

        // Main Menu: View list of quiz score statistics. Exercise the 'Main Menu' button.
        onView(withId(R.id.quizMainMenuBanner)).check(matches(withText("Main Menu")));
        onView(withId(R.id.viewQuizScoreStatsRadioButton)).perform(click());
        onView(withId(R.id.confirmButton)).perform(click());

        onView(withId(R.id.statsForStudentBanner)).check(matches(withText("Statistics for " + username)));
        onView(withId(R.id.quizzesPlayedByUsernameMultiSelect)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(quizName))).check(matches(withText(quizName)));
        onData(allOf(is(instanceOf(String.class)), is(quizName))).perform(click());
        onView(withId(R.id.returnMainMenuButton)).perform(click());

        // Main Menu: View list of quiz score statistics. Exercise the 'View Stats' button.
        onView(withId(R.id.quizMainMenuBanner)).check(matches(withText("Main Menu")));
        onView(withId(R.id.viewQuizScoreStatsRadioButton)).perform(click());
        onView(withId(R.id.confirmButton)).perform(click());

        onView(withId(R.id.statsForStudentBanner)).check(matches(withText("Statistics for " + username)));
        onView(withId(R.id.quizzesPlayedByUsernameMultiSelect)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(quizName))).check(matches(withText(quizName)));
        onData(allOf(is(instanceOf(String.class)), is(quizName))).perform(click());
        onView(withId(R.id.viewStatisticsButton)).perform(click());

        // Main Menu: View list of quiz score statistics.
        onView(withId(R.id.statsForQuizBanner)).check(matches(withText("Statistics for Quiz")));
        onView(withId(R.id.showStats)).check(matches(isDisplayed()));
        onView(withId(R.id.showStats)).check(matches(not(withText(""))));
        onView(withId(R.id.returnMainMenuButton)).perform(click());
        onView(withId(R.id.logoutButton)).perform(click());
    }

    /**
     * Private method that tests to ensure that a quiz created by a registered user can be successfully removed.
     *
     * @param username Username of a registered user.
     * @param quizName Quiz name.
     */
    private void LoginUserRemoveQuiz(String username, String quizName) {

        // Login with newly created user.
        onView(withId(R.id.welcomeBanner)).check(matches(withText("Welcome to SDPVocab")));
        onView(withId(R.id.loginUsername)).perform(clearText(), typeText(username), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform((click()));

        // Main Menu: Remove a quiz.
        onView(withId(R.id.quizMainMenuBanner)).check(matches(withText("Main Menu")));
        onView(withId(R.id.removeQuizRadioButton)).perform(click());
        onView(withId(R.id.confirmButton)).perform(click());
        onView(withId(R.id.removeQuizBanner)).check(matches(withText("Remove Quiz")));
        onView(withId(R.id.removeQuizMultiSelect)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(quizName))).check(matches(withText(quizName)));
        onData(allOf(is(instanceOf(String.class)), is(quizName))).perform(click());
        onView(withId(R.id.confirmButton)).perform(click());

        // Verify that the quiz was removed.
        onView(withId(R.id.removeQuizRadioButton)).perform(click());
        onView(withId(R.id.confirmButton)).perform(click());
        onView(withId(R.id.removeQuizMultiSelect)).check(matches(not(withText(quizName))));
        onView(withId(R.id.confirmButton)).perform(click());
        onView(withId(R.id.logoutButton)).perform(click());
    }

    /**
     * Private method that generates a random number between 1 and 'bound'.
     *
     * @param bound Upper bound of random number.
     * @return Random number between 1 and 'bound'.
     */
    private int generateRandomNum(int bound) {

        Random rand = new Random();
        int randNum = rand.nextInt(1000) + 1;

        return randNum;
    }
}

