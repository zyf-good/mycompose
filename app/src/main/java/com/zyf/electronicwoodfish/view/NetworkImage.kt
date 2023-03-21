package com.zyf.electronicwoodfish.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

/**
 * @author zengyifeng
 * @date createDate:2023-03-21
 * @brief description
 */
/*
* 加载网络图片
* */
@Composable
fun NetworkImage(){
    val url = "https://img-blog.csdnimg.cn/6343e1698dc34686b87dbf50f4eaf0f2.png"
    Column(
        Modifier.padding(top = 50.dp)
    ){
        AsyncImage(
            model = url,
            contentDescription = null
        )



        val modelBuilder = ImageRequest.Builder(LocalContext.current)
            .data(url ?: "")
            .crossfade(false)
            .allowHardware(true)
            .build()

        Image(
            painter = rememberAsyncImagePainter(
                model = modelBuilder
            ),
            contentDescription = "头像",
        )
    }
}