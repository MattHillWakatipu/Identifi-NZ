package fi.co_de.identifi_nz


import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.launchActivity
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import fi.co_de.identifi_nz.ui.home.HomeFragment
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NavigationTests {

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    // @Test
    // fun nav_controller_test() {
    //     // Init nav controller
    //     val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
    //
    //     // Launch starting fragment
    //     val startScenario =
    //         launchFragmentInContainer<HomeFragment>(themeResId = R.style.Theme_IdentifiNZ)
    //
    //     // Configure nav controller
    //     startScenario.onFragment { fragment ->
    //         navController.setGraph(R.navigation.mobile_navigation)
    //         Navigation.setViewNavController(fragment.requireView(), navController)
    //     }
    //
    //     // Check destination is correct
    //     assertEquals(navController.currentDestination?.id, R.id.nav_profile)
    // }

    @Test
    fun drawer_navigate_to_profile() {
        check_navigation(R.id.nav_profile)
    }

    @Test
    fun drawer_navigate_to_upload() {
        check_navigation(R.id.nav_upload)
    }

    @Test
    fun drawer_navigate_to_verify() {
        check_navigation(R.id.nav_verify)
    }

    @Test
    fun drawer_navigate_to_home() {
        check_navigation(R.id.nav_profile)
        check_navigation(R.id.nav_home)
    }

    private fun check_navigation(destinationId: Int) {
        onView(withId(R.id.drawer_layout))
            .perform(DrawerActions.open())

        onView(withId(R.id.nav_view))
            .perform(NavigationViewActions.navigateTo(destinationId))

        onView(withId(destinationId))
            .check(matches(isDisplayed()))
    }
}