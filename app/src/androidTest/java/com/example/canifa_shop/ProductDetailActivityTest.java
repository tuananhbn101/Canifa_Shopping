package com.example.canifa_shop;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.app.Activity;
import android.content.Context;

import androidx.activity.result.ActivityResult;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.TypeTextAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import com.example.canifa_shop.Login.LoginActivity;
import com.example.canifa_shop.Product.ProductDetailActivity;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ProductDetailActivityTest {
    @Rule
    public ActivityScenarioRule activityTestRule = new ActivityScenarioRule<>(ProductDetailActivity.class);
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.canifa_shop", appContext.getPackageName());
    }
    @Test
    public void insert(){
        onView(withId(R.id.etNameProduct))
                .perform(new TypeTextAction("thanhthao"),closeSoftKeyboard());
        onView(withId(R.id.etBardCodeProduct))
                .perform(new TypeTextAction("thao123"),closeSoftKeyboard());
        onView(withId(R.id.etAmountProduct))
                .perform(new TypeTextAction("10"),closeSoftKeyboard());
        onView(withId(R.id.etPriceImport))
                .perform(new TypeTextAction("125000"),closeSoftKeyboard());
        onView(withId(R.id.etPriceSell))
                .perform(new TypeTextAction("150000"),closeSoftKeyboard());
        onView(withId(R.id.etNote))
                .perform(new TypeTextAction("thao123"),closeSoftKeyboard());
        Intents.init();
        Espresso.onView(ViewMatchers.withId(R.id.btnControl)).perform(ViewActions.scrollTo(),ViewActions.click());
        Intents.release();
    }
}