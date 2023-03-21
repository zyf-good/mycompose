package com.zyf.electronicwoodfish

import android.app.Activity
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Created by ssk on 2022/4/17.
 */

/**
 * 设置为沉浸式状态栏
 */
fun Activity.transparentStatusBar() {
    WindowCompat.setDecorFitsSystemWindows(window, false)
}

/**
 * 状态栏反色
 */
fun Activity.setAndroidNativeLightStatusBar() {
    val decor = window.decorView
    val isDark = resources.configuration.uiMode == 0x21
    if (!isDark) {
        decor.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    } else {
        decor.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }



}
