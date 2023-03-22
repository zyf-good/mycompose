package com.zyf.electronicwoodfish.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.zyf.electronicwoodfish.util.LeetcodeUtil

/**
 * @author zengyifeng
 * @date createDate:2023-03-21
 * @brief description
 */

/*
* 力扣
* */
@Composable
fun Leetcode(){
    val list = mutableListOf<Int>()
    list.add(1)
    list.add(2)
    list.add(3)
    val list1 = mutableListOf<Int>()
    list1.add(3)
    list1.add(2)
    list1.add(1)
    var arrays = mutableListOf<IntArray>()
    arrays.add(list.toIntArray())
    arrays.add(list1.toIntArray())
    val answer = LeetcodeUtil.maximumWealth(arrays.toTypedArray())

    Row (
        Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "返回$answer", color = Color.White, fontSize = 30.sp)
    }
}