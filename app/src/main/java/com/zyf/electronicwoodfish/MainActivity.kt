package com.zyf.electronicwoodfish


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.zyf.electronicwoodfish.nav.InitNavController
import com.zyf.electronicwoodfish.ui.theme.ElectronicWoodfishComposeTheme
import com.zyf.electronicwoodfish.view.setFixSystemBarsColor

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transparentStatusBar()
        setAndroidNativeLightStatusBar()
        setContent {
            ElectronicWoodfishComposeTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()

                ) {
                    val navController = rememberAnimatedNavController()
                    InitNavController(navController = navController, context = this@MainActivity)
                    setFixSystemBarsColor()
                }
            }
        }
    }
}
