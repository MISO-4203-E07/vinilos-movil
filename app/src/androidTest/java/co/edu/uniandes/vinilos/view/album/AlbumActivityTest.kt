package co.edu.uniandes.vinilos.view.album

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import co.edu.uniandes.vinilos.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class AlbumActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(AlbumActivity::class.java)

    @Before
    fun setup() {
        Thread.sleep(3000)
    }

    @Test
    fun isListRecycleViewVisible_onAppLaunch() {
        onView(withId(R.id.rvAlbums)).check(matches(isDisplayed()))
    }

    @Test
    fun isAlbumItemViewVisible_onAppLaunch() {
        onView(withText("Buscando América")).check(matches(isDisplayed()))
        onView(withId(R.id.rvAlbums))
            .perform(swipeUp())
        onView(withText("A Day at the Races")).check(matches(isDisplayed()))
    }
}
