package co.edu.uniandes.vinilos.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.util.HumanReadables
import androidx.test.espresso.util.TreeIterables
import co.edu.uniandes.vinilos.R
import org.hamcrest.*
import java.util.concurrent.TimeoutException

open class BaseTest {
    protected fun waitForView(
        viewMatcher: Matcher<View>,
        timeout: Long = 10000,
        waitForDisplayed: Boolean = true
    ): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return Matchers.any(View::class.java)
            }

            override fun getDescription(): String {
                val matcherDescription = StringDescription()
                viewMatcher.describeTo(matcherDescription)
                return "wait for a specific view <$matcherDescription> to be ${if (waitForDisplayed) "displayed" else "not displayed during $timeout millis."}"
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()
                val startTime = System.currentTimeMillis()
                val endTime = startTime + timeout
                val visibleMatcher = ViewMatchers.isDisplayed()

                do {
                    val viewVisible = TreeIterables.breadthFirstViewTraversal(view)
                        .any { viewMatcher.matches(it) && visibleMatcher.matches(it) }

                    if (viewVisible == waitForDisplayed) return
                    uiController.loopMainThreadForAtLeast(50)
                } while (System.currentTimeMillis() < endTime)

                throw PerformException.Builder()
                    .withActionDescription(this.description)
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(TimeoutException())
                    .build()
            }
        }
    }

    protected fun getNavBar() = Espresso.onView(
        Matchers.allOf(
            ViewMatchers.withContentDescription("Open navigation drawer"),
            childAtPosition(
                Matchers.allOf(
                    ViewMatchers.withId(R.id.toolbar),
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("com.google.android.material.appbar.AppBarLayout")),
                        0
                    )
                ),
                1
            ),
            ViewMatchers.isDisplayed()
        )
    )

    protected fun getItemNavBar(item: Int) = Espresso.onView(
        Matchers.allOf(ViewMatchers.withId(item))
    )

    protected fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    protected fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?> {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {

            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                return itemMatcher.matches(viewHolder?.itemView)
            }
        }
    }

    protected fun returnAction() {
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withContentDescription("Navigate up"),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.action_bar),
                        childAtPosition(
                            ViewMatchers.withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        ).perform(ViewActions.click())
    }
}