package co.edu.uniandes.vinilos.view.album


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
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
class DetailAlbumActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(AlbumActivity::class.java)

    @Before
    fun setup() {
        Thread.sleep(3000)
    }

    @Test
    fun isTextViewVisible_onAppLaunch() {
        // Entrar al detalle de un album primero
        onView(withText("Buscando América")).perform(click())

        // Assert
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvArtist)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenre)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.recordLabel)).check(matches(isDisplayed()))
    }

    @Test
    fun isTextDisplayedOnTextViewTitle_onAppLaunch() {
        // Entrar al detalle de un album primero
        onView(withText("Poeta del pueblo")).perform(click())

        // Assert
        onView(withText("Poeta del pueblo")).check(matches(isDisplayed()))
        onView(withText("Rubén Blades Bellido de Luna")).check(matches(isDisplayed()))
        onView(withText("Salsa")).check(matches(isDisplayed()))
    }
}
