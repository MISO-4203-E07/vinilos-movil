package co.edu.uniandes.vinilos.view.album

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
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
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.*


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

    @Test
    fun givenAlbum_WhenWantToReturn_ShowListAlbum() {

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        getNavBar().perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        getItemNavBar(R.id.nav_album).perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        getAddButton().perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        val materialButton = onView(
            allOf(
                withId(R.id.btnCancel), withText("Cancel"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val textView = onView(
            allOf(
                withText("Album"),
                withParent(
                    allOf(
                        withId(R.id.toolbar),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Album")))
    }

    @Test
    fun givenAlbum_WhenAlbumIsValid_FillAllFields() {

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        getNavBar().perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        getItemNavBar(R.id.nav_album).perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        getAddButton().perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        val etName = onView(withId(R.id.etName))
        etName.perform(click())
        etName.perform(typeText("Test"))
        val etNameChanged = onView(withId(R.id.etName))
        etNameChanged.check(matches(withText("Test")))

        val etCover = onView(withId(R.id.etCover))
        etCover.perform(click())
        etCover.perform(typeText("Test"))
        val etCoverChanged = onView(withId(R.id.etCover))
        etCoverChanged.check(matches(withText("Test")))

        val etDescription = onView(withId(R.id.etDescription))
        etDescription.perform(click())
        etDescription.perform(typeText("Test"))
        val etDescriptionChanged = onView(withId(R.id.etDescription))
        etDescriptionChanged.check(matches(withText("Test")))

        val etDate = onView(withId(R.id.etReleaseDate))
        etDate.perform(click())

        getCalendar().perform(ViewActions.scrollTo(), click())


    }

    @Test
    fun givenAlbum_WhenAlbumIsValid_CreateAlbum() {

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        getNavBar().perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        getItemNavBar(R.id.nav_album).perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        val view = mActivityTestRule.activity.findViewById<RecyclerView>(R.id.rvAlbums)
        val dataCount = (view.adapter as AlbumAdapter).data.size
        getAddButton().perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        val etName = onView(withId(R.id.etName))
        etName.perform(click())
        etName.perform(typeText("Test"))
        val etNameChanged = onView(withId(R.id.etName))
        etNameChanged.check(matches(withText("Test")))

        val etCover = onView(withId(R.id.etCover))
        etCover.perform(click())
        etCover.perform(typeText("Test"))
        val etCoverChanged = onView(withId(R.id.etCover))
        etCoverChanged.check(matches(withText("Test")))

        val etDescription = onView(withId(R.id.etDescription))
        etDescription.perform(click())
        etDescription.perform(typeText("Test"), closeSoftKeyboard())
        val etDescriptionChanged = onView(withId(R.id.etDescription))
        etDescriptionChanged.check(matches(withText("Test")))

        val etDate = onView(withId(R.id.etReleaseDate))
        etDate.perform(click())
        getCalendar().perform(ViewActions.scrollTo(), click())

        getSpinnerGenre().perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        onData(
            allOf(
                `is`(instanceOf(String::class.java)),
                `is`("Salsa")
            )
        ).perform(click())


        getSpinnerLabel().perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        onData(
            allOf(
                `is`(instanceOf(String::class.java)),
                `is`("EMI")
            )
        ).perform(click())



        getCreateButton().perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        val viewCreated = mActivityTestRule.activity.findViewById<RecyclerView>(R.id.rvAlbums)
        val dataCountCreated = (viewCreated.adapter as AlbumAdapter).data.size
        assert(dataCount < dataCountCreated)

    }

    private fun getSpinnerLabel() = onView(
        allOf(
            withId(R.id.spLabel),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0
                ),
                5
            )
        )
    )

    private fun getSpinnerGenre() = onView(
        allOf(
            withId(R.id.spGenre),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0
                ),
                4
            )
        )
    )

    @Test
    fun givenAlbum_WhenAlbumIsInvalid_ShowError() {

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        getNavBar().perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        getItemNavBar(R.id.nav_album).perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        getAddButton().perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        getCreateButton().perform(click())
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("There are empty fields. Please fill all fields")))

    }

    private fun getCreateButton() = onView(
        allOf(
            withId(R.id.btnCreate), withText("Create"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0
                ),
                7
            ),
            isDisplayed()
        )
    )

    private fun getCalendar() = onView(
        allOf(
            withId(android.R.id.button1), withText("OK"),
            childAtPosition(
                childAtPosition(
                    withClassName(`is`("android.widget.ScrollView")),
                    0
                ),
                3
            )
        )
    )

    private fun getAddButton() = onView(
        allOf(
            withId(R.id.fabCreateAlbum),
            childAtPosition(
                childAtPosition(
                    withId(R.id.nav_host_fragment_content_main),
                    0
                ),
                1
            ),
            isDisplayed()
        )
    )

}
