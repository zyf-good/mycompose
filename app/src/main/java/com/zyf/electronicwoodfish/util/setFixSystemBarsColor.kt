package com.zyf.electronicwoodfish.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * @author zengyifeng
 * @date createDate:2023-03-21
 * @brief description  设置状态栏透明，图标白色
 */

@Composable
fun setFixSystemBarsColor() {
    val sysUiController = rememberSystemUiController()
    sysUiController.setSystemBarsColor(Color.Transparent, false)
}