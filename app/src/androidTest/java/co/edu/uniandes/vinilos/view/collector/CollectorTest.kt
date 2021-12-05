package co.edu.uniandes.vinilos.view.collector

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import co.edu.uniandes.vinilos.R
import co.edu.uniandes.vinilos.base.BaseTest
import co.edu.uniandes.vinilos.view.MainActivity
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@LargeTest
@RunWith(AndroidJUnit4::class)
class CollectorTest : BaseTest() {


    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun givenArtists_WhenArtistIsLoad_List() {

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        getNavBar().perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        getItemNavBar(R.id.nav_collector).perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        val recyclerView = onView(
            allOf(withId(R.id.rvCollectors))
        )
        recyclerView.check(matches(isDisplayed()))

    }

    @Test
    fun givenCollector_WhenCollectorIsLoad_CheckAtLeastOne() {
        Thread.sleep(TimeUnit.SECONDS.toMillis(2))

        getNavBar().perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(2))

        getItemNavBar(R.id.nav_collector).perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(2))

        onView(withId(R.id.rvCollectors))
            .perform(scrollToPosition<ViewHolder>(0))
            .check(matches(atPosition(0, isDisplayed())))
    }

    @Test
    fun givenCollector_WhenCollectorIsNotLoaded_CheckAtLeastOneNotExits() {
        Thread.sleep(TimeUnit.SECONDS.toMillis(2))

        getNavBar().perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        getItemNavBar(R.id.nav_collector).perform(click())

        onView(withId(R.id.rvCollectors))
            .check(matches(atPosition(100, not(isDisplayed()))))
    }


    @Test
    fun givenCollector_WhenCollectorIsValid_SeeDetails() {
        Thread.sleep(TimeUnit.SECONDS.toMillis(2))

        getNavBar().perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        getItemNavBar(R.id.nav_collector).perform(click())

        onView(withId(R.id.rvCollectors)).check(matches(atPosition(100, not(isDisplayed()))))

        val recyclerView = onView(
            allOf(
                withId(R.id.rvCollectors),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, click()))

        val viewGroup = onView(
            allOf(
                withParent(
                    allOf(
                        withId(android.R.id.content),
                        withParent(withId(R.id.decor_content_parent))
                    )
                ),
                isDisplayed()
            )
        )
        viewGroup.check(matches(isDisplayed()))
    }


    @Test
    fun givenCollector_WhenCollectorIsValid_SeeDetailsOfAlbumAndReturn() {
        Thread.sleep(TimeUnit.SECONDS.toMillis(2))

        getNavBar().perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        getItemNavBar(R.id.nav_collector).perform(click())

        onView(withId(R.id.rvCollectors)).check(matches(atPosition(100, not(isDisplayed()))))

        val recyclerView = onView(
            allOf(
                withId(R.id.rvCollectors),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, click()))

        val viewGroup = onView(
            allOf(
                withParent(
                    allOf(
                        withId(android.R.id.content),
                        withParent(withId(R.id.decor_content_parent))
                    )
                ),
                isDisplayed()
            )
        )
        viewGroup.check(matches(isDisplayed()))
        val recyclerView2 = onView(
            allOf(
                withId(R.id.rvCollectorAlbums),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    4
                )
            )
        )
        recyclerView2.perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, click()))

        returnAction();
        returnAction();

        val recyclerView3 = onView(
            allOf(
                withId(R.id.rvCollectors),
                withParent(withParent(withId(R.id.nav_host_fragment_content_main))),
                isDisplayed()
            )
        )
        recyclerView3.check(matches(isDisplayed()))

        val recyclerView4 = onView(
            allOf(
                withId(R.id.rvCollectors),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView4.perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, click()))
    }


}
