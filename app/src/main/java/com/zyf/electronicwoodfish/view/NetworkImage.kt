package com.zyf.electronicwoodfish.view

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.zyf.electronicwoodfish.util.cdp
import com.zyf.electronicwoodfish.R

/**
 * @author zengyifeng
 * @date createDate:2023-03-21
 * @brief description
 */
/*
* 加载网络图片
* */
@Composable
fun NetworkImage(context:Context){
    val url = rememberSaveable {
        mutableStateOf("https://img-blog.csdnimg.cn/6343e1698dc34686b87dbf50f4eaf0f2.png")
    }
    Column (
        Modifier.fillMaxSize()
            .padding(top = 150.cdp),
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.Start
    ){
        Text(text = "网络图片的地址：")
        TextField( value = url.value, onValueChange = {
            url.value = it
        } )

        Row (
            Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center ,
            verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImage(
                model = url.value,
                contentDescription = "AsyncImage"
            )



            val modelBuilder = ImageRequest.Builder(LocalContext.current)
                .data(url.value)
                .crossfade(false)
                .allowHardware(true)
                .build()

            Image(
                painter = rememberAsyncImagePainter(
                    model = modelBuilder
                ),
                contentDescription = "modelBuilder",
            )
        }



    }
}