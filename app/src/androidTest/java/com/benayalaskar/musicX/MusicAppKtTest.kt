package com.benayalaskar.musicX

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.benayalaskar.musicX.model.MusicData
import com.benayalaskar.musicX.navigation.Screen
import com.benayalaskar.musicX.ui.theme.ListMusikTheme
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MusicAppKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeTestRule.setContent {
            ListMusikTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                MusikApp(navController = navController)
            }
        }
    }

//      Menguji Screen Home
    @Test

    fun a_navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

//        Menguji ketika list diklik maka akan tampil halaman details dengan benar,
    @Test
    fun b_navHost_clickItem_navigatesToDetailWithData() {
        composeTestRule.onNodeWithText(MusicData.music[2].judul).performClick()
        navController.assertCurrentRouteName(Screen.DetailMusik.route)
        composeTestRule.onNodeWithText(MusicData.music[2].judul)
            .assertIsDisplayed()
    }

    //    mengetes navigasi 
    @Test
    fun c_navHost_bottomNavigation_working() {
        composeTestRule.onNodeWithStringId(R.string.menu_about).performClick()
        navController.assertCurrentRouteName(Screen.About.route)
        composeTestRule.onNodeWithStringId(R.string.menu_favorite).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
    }

    //mengecek apakah ketika menambahkan favorites maka langsung ke screen favorites dan lansung ada list music yang ditambahkan
    //Dan mengecek ketika favorites dihapus apakah list nya terhapus, dan jika list kosong maka akan tampil favorites kosong!!
    @Test
    fun d_navHost_favoriteAdd_favoriteDelete() {
        composeTestRule.onNodeWithText(MusicData.music[4].judul).performClick()
        navController.assertCurrentRouteName(Screen.DetailMusik.route)
        composeTestRule.onNodeWithContentDescription("Favorite Button").performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithText(MusicData.music[4].judul)
            .assertIsDisplayed()


        Thread.sleep(2000)

        composeTestRule.onNodeWithText(MusicData.music[4].judul).performClick()
        navController.assertCurrentRouteName(Screen.DetailMusik.route)

        composeTestRule.onNodeWithContentDescription("Delete Favorite Button").performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithText(MusicData.music[4].judul)
            .assertDoesNotExist()
        composeTestRule.onNodeWithText("favorites kosong!!")
            .assertIsDisplayed()
    }


}