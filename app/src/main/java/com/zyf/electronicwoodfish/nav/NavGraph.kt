package com.zyf.electronicwoodfish.nav

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.zyf.electronicwoodfish.view.*

/**
 * @author zengyifeng
 * @date createDate:2023-03-21
 * @brief description
 */
object NavController {
    @SuppressLint("StaticFieldLeak")
    lateinit var instance: NavHostController

}


@OptIn(ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun InitNavController(
    navController: NavHostController,
    startDestination: String = RouterUrls.SCREENPAGE,
    context: Context
) {
    NavController.instance = navController
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(
            RouterUrls.SCREENPAGE
        ) {
            ScreenPage(context = context)
        }

        composable(
            RouterUrls.SURPROSEPAGE
        ) {
            SurprisePage()
        }

        composable(
            RouterUrls.LEETCODE
        ) {
            Leetcode()
        }

        composable(
            RouterUrls.XMLVIEW
        ) {
            XmlView()
        }

        composable(
            RouterUrls.CUSTOMIMAGE
        ) {
            CustomImage()
        }

        composable(
            RouterUrls.NETWORKIMAGE
        ) {
            NetworkImage(context)
        }

    }


}