package com.zyf.electronicwoodfish.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.zyf.electronicwoodfish.nav.NavController
import com.zyf.electronicwoodfish.nav.RouterUrls

/**
 * @author zengyifeng
 * @date createDate:2023-03-21
 * @brief description  设置状态栏透明，图标白色
 */

@Composable
fun setFixSystemBarsColor() {
    val sysUiController = rememberSystemUiController()
    val curRouteName = NavController.instance.currentBackStackEntryAsState().value?.destination?.route
    if (curRouteName == RouterUrls.SCREENPAGE){
        sysUiController.setSystemBarsColor(Color.Transparent, false)
    }else{
        sysUiController.setSystemBarsColor(Color.Transparent, true)
    }

}