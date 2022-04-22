package fi.co_de.identifi_nz


import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import fi.co_de.identifi_nz.ui.home.HomeFragment
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NavigationTests {

    @Test
    @Before
    fun setup() {
        print("OK")
    }

    @Test
    fun starts_on_home() {
        // Init nav controller
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        // Launch starting fragment
        val startScenario =
            launchFragmentInContainer<HomeFragment>(themeResId = R.style.Theme_IdentifiNZ)

        // Configure nav controller
        startScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        // Click button
        // onView(withId(R.id.app_bar_main)).perform(click())

        // Check destination is correct
        assertEquals(navController.currentDestination?.id, R.id.nav_home)
    }

    @Test
    fun navigate_to_home() {
        TODO()
    }

    @Test
    fun navigate_to_profile() {
        TODO()
    }

    @Test
    fun navigate_to_upload() {
        TODO()
    }

    @Test
    fun navigate_to_verify() {
        TODO()
    }
}