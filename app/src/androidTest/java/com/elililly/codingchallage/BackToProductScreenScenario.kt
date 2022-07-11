package com.elililly.codingchallage

import android.view.View
import androidx.test.filters.LargeTest
import org.junit.runner.RunWith
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.elililly.codingchallage.MainActivity
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import com.elililly.codingchallage.R
import com.elililly.codingchallage.BackToProductScreenScenario
import androidx.test.espresso.action.ViewActions
import android.view.ViewParent
import android.view.ViewGroup
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test

@LargeTest
@RunWith(AndroidJUnit4::class)
class BackToProductScreenScenario {
    @Rule
    var mActivityScenarioRule = ActivityScenarioRule(
        MainActivity::class.java
    )

    @Test
    fun backToProductScreenScenario() {
        val appCompatImageView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.plus),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.LinearLayout")),
                        3
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatImageView.perform(ViewActions.click())
        val appCompatImageView2 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.plus),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.LinearLayout")),
                        3
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatImageView2.perform(ViewActions.click())
        val appCompatImageView3 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.plus),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.LinearLayout")),
                        3
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatImageView3.perform(ViewActions.click())
        val appCompatImageView4 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.plus),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.LinearLayout")),
                        3
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatImageView4.perform(ViewActions.click())
        val appCompatImageView5 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.plus),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.LinearLayout")),
                        3
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatImageView5.perform(ViewActions.click())
        val appCompatImageView6 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.minus),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.LinearLayout")),
                        3
                    ),
                    2
                ),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatImageView6.perform(ViewActions.click())
        val materialButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.orderSummaryBtn), ViewMatchers.withText("Checkout"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                        2
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        materialButton.perform(ViewActions.click())
        Espresso.pressBack()
        Espresso.pressBack()
    }

    companion object {
        private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int
        ): Matcher<View> {
            return object : TypeSafeMatcher<View>() {
                override fun describeTo(description: Description) {
                    description.appendText("Child at position $position in parent ")
                    parentMatcher.describeTo(description)
                }

                public override fun matchesSafely(view: View): Boolean {
                    val parent = view.parent
                    return (parent is ViewGroup && parentMatcher.matches(parent)
                            && view == parent.getChildAt(position))
                }
            }
        }
    }
}