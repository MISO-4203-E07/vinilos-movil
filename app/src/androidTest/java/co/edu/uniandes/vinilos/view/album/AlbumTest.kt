package co.edu.uniandes.vinilos.view.album

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import co.edu.uniandes.vinilos.R
import co.edu.uniandes.vinilos.base.BaseTest
import co.edu.uniandes.vinilos.view.MainActivity
import org.hamcrest.Matchers.*
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@LargeTest
@RunWith(AndroidJUnit4::class)
class AlbumTest : BaseTest() {


    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun givenAlbum_WhenAlbumIsLoad_CheckDetail() {

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        getNavBar().perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))


        getItemNavBar(R.id.nav_album).perform(click())

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

        val navigationMenuItemView = getItemNavBar(R.id.nav_album)
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

        val navigationMenuItemView = getItemNavBar(R.id.nav_album)
        navigationMenuItemView.perform(click())

        onView(withId(R.id.rvAlbums))
            .check(matches(atPosition(100, not(isDisplayed()))));
    }

    @Test
    fun givenAlbum_WhenAlbumIsLoad_CheckDetailAndReturnSuccess() {

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        getNavBar().perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        getItemNavBar(R.id.nav_album).perform(click())
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

        returnAction()

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        recyclerView.check(matches(isDisplayed()))
    }

}
