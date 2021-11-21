package co.edu.uniandes.vinilos.view.album

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import co.edu.uniandes.vinilos.R
import co.edu.uniandes.vinilos.view.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@LargeTest
@RunWith(AndroidJUnit4::class)
class AlbumTest {


    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun givenAlbum_WhenAlbumIsLoad_CheckDetail() {

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        getNavBar().perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))


        getItemNavBar().perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        val recyclerView = onView(
            allOf(
                withId(R.id.rvAlbums),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0
                )
            )
        )

        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        val textView = onView(
            allOf(
                withId(R.id.tvGenre),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))

        val label = onView(
            allOf(
                withId(R.id.tracks),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        label.check(matches(withText("Tracks")))
    }

    @Test
    fun givenAlbums_WhenAlbumsIsLoad_CheckAtLeastOne() {
        Thread.sleep(TimeUnit.SECONDS.toMillis(2))

        val appCompatImageButton = getNavBar()
        appCompatImageButton.perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(2))

        val navigationMenuItemView = getItemNavBar()
        navigationMenuItemView.perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(2))

        onView(withId(R.id.rvAlbums))
            .perform(scrollToPosition<ViewHolder>(0))
            .check(matches(atPosition(0, isDisplayed())));
    }

    @Test
    fun givenAlbums_WhenAlbumsIsNotLoaded_CheckAtLeastOneNotExits() {
        Thread.sleep(TimeUnit.SECONDS.toMillis(2))

        val appCompatImageButton = getNavBar()
        appCompatImageButton.perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        val navigationMenuItemView = getItemNavBar()
        navigationMenuItemView.perform(click())

        onView(withId(R.id.rvAlbums))
            .check(matches(atPosition(100, not(isDisplayed()))));
    }

    @Test
    fun givenAlbum_WhenAlbumIsLoad_CheckDetailAndReturnSuccess() {

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        getNavBar().perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        getItemNavBar().perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        val recyclerView = onView(
            allOf(
                withId(R.id.rvAlbums),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        val textView = onView(
            allOf(
                withId(R.id.tvGenre),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        ).perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        recyclerView.check(matches(isDisplayed()))
    }

    private fun getItemNavBar() = onView(
        allOf(
            withId(R.id.nav_album),
            childAtPosition(
                allOf(
                    withId(R.id.design_navigation_view),
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    )
                ),
                2
            ),
            isDisplayed()
        )
    )

    private fun getNavBar() = onView(
        allOf(
            withContentDescription("Open navigation drawer"),
            childAtPosition(
                allOf(
                    withId(R.id.toolbar),
                    childAtPosition(
                        withClassName(`is`("com.google.android.material.appbar.AppBarLayout")),
                        0
                    )
                ),
                1
            ),
            isDisplayed()
        )
    )

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
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    private fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?> {
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

    fun checkViewIsDisplayed(viewId: Int): ViewInteraction {
        return onView(withId(viewId)).check(matches(isDisplayed()))
    }
}
