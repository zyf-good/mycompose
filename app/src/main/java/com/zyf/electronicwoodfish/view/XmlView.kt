package com.zyf.electronicwoodfish.view

import android.widget.Button
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

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

    AndroidView(factory = { context ->
        Button(context).apply{
            setOnClickListener{
                selectedItem += 1
            }
        }
    },
        modifier = Modifier.fillMaxSize(),
        update = {view ->
            view.text = selectedItem.toString()
        })
}