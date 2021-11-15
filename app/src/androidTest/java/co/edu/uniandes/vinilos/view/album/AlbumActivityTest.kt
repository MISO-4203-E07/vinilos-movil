package co.edu.uniandes.vinilos.view.album

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import co.edu.uniandes.vinilos.R
import co.edu.uniandes.vinilos.base.BaseTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class AlbumActivityTest : BaseTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(AlbumActivity::class.java)

    @Test
    fun isListRecycleViewVisible_onAppLaunch() {
        onView(isRoot()).perform(waitForView(withId(R.id.rvAlbums)))
        onView(withId(R.id.rvAlbums)).check(matches(isDisplayed()))
    }

    @Test
    fun isAlbumItemViewVisible_onAppLaunch() {
        val firstTexView = "Buscando América"
        val secondTexView = "A Day at the Races"
        onView(isRoot()).perform(waitForView(withText(firstTexView)))
        onView(withText(firstTexView)).check(matches(isDisplayed()))
        onView(withId(R.id.rvAlbums))
            .perform(swipeUp())
        onView(withText(secondTexView)).check(matches(isDisplayed()))
    }
}
