package co.edu.uniandes.vinilos.view.artist

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
class ArtistTest : BaseTest() {


    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun givenArtists_WhenArtistIsLoad_CheckDetail() {

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        getNavBar().perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        getItemNavBar(R.id.nav_home).perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        val recyclerView = onView(
            allOf(withId(R.id.tvArtist))
        )

        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        val textView = onView(
            allOf(
                withId(R.id.tvName),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))
    }

    @Test
    fun givenArtists_WhenArtistsIsLoad_CheckAtLeastOne() {
        Thread.sleep(TimeUnit.SECONDS.toMillis(2))

        getNavBar().perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(2))

        getItemNavBar(R.id.nav_home).perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(2))

        onView(withId(R.id.tvArtist))
            .perform(scrollToPosition<ViewHolder>(0))
            .check(matches(atPosition(0, isDisplayed())))
    }

    @Test
    fun givenArtists_WhenArtistsIsNotLoaded_CheckAtLeastOneNotExits() {
        Thread.sleep(TimeUnit.SECONDS.toMillis(2))

        getNavBar().perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        getItemNavBar(R.id.nav_home).perform(click())

        onView(withId(R.id.tvArtist))
            .check(matches(atPosition(100, not(isDisplayed()))))
    }

    @Test
    fun givenArtists_WhenArtistIsLoad_CheckDetailAndReturnSuccess() {

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        getNavBar().perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        getItemNavBar(R.id.nav_home).perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        val recyclerView = onView(
            allOf(
                withId(R.id.tvArtist),
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
                withId(R.id.tvName),
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
