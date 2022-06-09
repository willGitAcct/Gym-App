package com.example.surgegym;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class LoginTest {

    @Rule
    public ActivityScenarioRule<Login> mActivityTestRule = new ActivityScenarioRule<Login>(Login.class);
    private String mName = "Test";

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testUserInputScenario() {
        Espresso.onView(withId(R.id.inputEmail)).perform(typeText(mName));
        Espresso.onView(withId(R.id.inputPassword)).perform(typeText(mName));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.inputEmail)).check(matches(withText(mName)));
        Espresso.onView(withId(R.id.inputPassword)).check(matches(withText(mName)));
    }

    @After
    public void tearDown() throws Exception {

    }
}
