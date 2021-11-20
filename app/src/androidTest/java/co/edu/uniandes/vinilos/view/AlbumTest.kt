package co.edu.uniandes.vinilos.view


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
import androidx.test.rule.ActivityTestRule
import co.edu.uniandes.vinilos.HiltTestActivity
import co.edu.uniandes.vinilos.R
import co.edu.uniandes.vinilos.data.model.AlbumModelTest
import co.edu.uniandes.vinilos.view.album.AlbumFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@HiltAndroidTest
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class AlbumTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityRule: ActivityTestRule<HiltTestActivity> =
        ActivityTestRule(HiltTestActivity::class.java)

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val testBundle = AlbumModelTest.testBundle

    @Before
    fun setUp() {
        hiltRule.inject()

    }

    @ExperimentalCoroutinesApi
    @Test
    fun testWithHilt() {
        launchFragmentInHiltContainer<AlbumFragment>()
        Thread.sleep(TimeUnit.SECONDS.toMillis(222))
        checkViewIsDisplayed(R.id.rvAlbums)
    }

    @Test
    fun givenAlbum_WhenAlbumIsLoad_CheckDetail() {

        Thread.sleep(TimeUnit.SECONDS.toMillis(2))

        val appCompatImageButton = getNavBar()
        appCompatImageButton.perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        val navigationMenuItemView = getItemNavBar()
        navigationMenuItemView.perform(click())

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

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

        val textView = onView(
            allOf(
                withId(R.id.tvGenre), withText("Salsa"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Salsa")))

        val appCompatImageButton2 = onView(
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
        )
        appCompatImageButton2.perform(click())
    }

    @Test
    fun givenAlbums_WhenAlbumsIsLoad_CheckAtLeastOne() {
        Thread.sleep(TimeUnit.SECONDS.toMillis(100))

        val appCompatImageButton = getNavBar()
        appCompatImageButton.perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        val navigationMenuItemView = getItemNavBar()
        navigationMenuItemView.perform(click())

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
            .perform(scrollToPosition<ViewHolder>(0))
            .check(matches(atPosition(0, isDisplayed())));
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

    fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?>? {
        checkNotNull(itemMatcher)
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                    ?: // has no item on such position
                    return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }

    fun checkViewIsDisplayed(viewId: Int): ViewInteraction {
        return onView(withId(viewId)).check(matches(isDisplayed()))
    }
}
