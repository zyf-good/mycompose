package com.zyf.electronicwoodfish.view

import android.widget.Button
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.zyf.electronicwoodfish.util.cdp

/**
 * @author zengyifeng
 * @date createDate:2023-03-21
 * @brief description
 */
/*
* compose嵌套原生view
* */
@Composable
fun XmlView(){
    var selectedItem by remember { mutableStateOf(0) }

    Row (
        Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AndroidView(factory = { context ->
            Button(context).apply {
                setOnClickListener {
                    selectedItem += 1
                }
            }
        },
            modifier = Modifier.size(200.cdp),
            update = { view ->
                view.text = selectedItem.toString()
            })
    }
}