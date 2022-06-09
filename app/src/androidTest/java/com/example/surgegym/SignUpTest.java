package com.example.surgegym;

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

public class SignUpTest {

    @Rule
    public ActivityScenarioRule<SignUp> mActivityTestRule = new ActivityScenarioRule<SignUp>(SignUp.class);
    private String mName = "Test";
    private String mPhone = "123";

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testUserInputScenario() {
        Espresso.onView(withId(R.id.inputFirstName)).perform(typeText(mName));
        Espresso.onView(withId(R.id.inputLastName)).perform(typeText(mName));
        Espresso.onView(withId(R.id.inputPhone)).perform(typeText(mPhone));
        Espresso.onView(withId(R.id.inputAddress)).perform(typeText(mName));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.inputFirstName)).check(matches(withText(mName)));
        Espresso.onView(withId(R.id.inputLastName)).check(matches(withText(mName)));
        Espresso.onView(withId(R.id.inputPhone)).check(matches(withText(mPhone)));
        Espresso.onView(withId(R.id.inputAddress)).check(matches(withText(mName)));
    }

    @After
    public void tearDown() throws Exception {

    }
}
