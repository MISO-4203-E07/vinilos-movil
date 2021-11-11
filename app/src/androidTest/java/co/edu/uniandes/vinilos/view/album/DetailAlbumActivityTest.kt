package co.edu.uniandes.vinilos.view.album


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
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
class DetailAlbumActivityTest : BaseTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(AlbumActivity::class.java)

    @Test
    fun isTextViewVisible_onAppLaunch() {
        // Entrar al detalle de un album primero
        val texView = "Buscando América"
        onView(isRoot()).perform(waitForView(withText(texView)))
        onView(withText(texView)).perform(click())

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
        val firstTexView = "Poeta del pueblo"
        onView(isRoot()).perform(waitForView(withText(firstTexView)))
        onView(withText(firstTexView)).perform(click())

        // Assert
        onView(withText(firstTexView)).check(matches(isDisplayed()))
        onView(withText("Rubén Blades Bellido de Luna")).check(matches(isDisplayed()))
        onView(withText("Salsa")).check(matches(isDisplayed()))
    }
}
