package com.zyf.electronicwoodfish.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zyf.electronicwoodfish.R

/**
 * @author zengyifeng
 * @date createDate:2023-03-21
 * @brief description
 */
/*
* Image相关
* */
@Composable
@Preview(showBackground = true ,showSystemUi = true)
fun CustomImage(){
    val rainbowColorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            )
        )
    }
    val imageModifier = Modifier
        .size(150.dp)
        .border(
            BorderStroke(4.dp, rainbowColorsBrush),
            RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp)
        )
        .background(Color.Yellow)
        .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
        .aspectRatio(16f / 9f)
        .blur(
            radiusX = 10.dp,
            radiusY = 10.dp,
            edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
        )
    val contentScale = ContentScale.Crop

    val contrast = 2f // 0f..10f (1 should be default)
    val brightness = -180f // -255f..255f (0 should be default)
    val colorMatrix = floatArrayOf(
        contrast, 0f, 0f, 0f, brightness,
        0f, contrast, 0f, 0f, brightness,
        0f, 0f, contrast, 0f, brightness,
        0f, 0f, 0f, 1f, 0f
    )

    Row (
        Modifier.fillMaxSize()
            .background(Color.White),
        horizontalArrangement = Arrangement.Center ,
        verticalAlignment = Alignment.CenterVertically
    ){

        Column {
            Image(
                painter = painterResource(id = R.mipmap.girl) ,
                contentDescription = "小姐姐",
                modifier = imageModifier,
                contentScale = contentScale,
                colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix))

            )
        }
        Column(
            Modifier.padding(start =  10.dp)
        ) {
            Image(
                painter = painterResource(id = R.mipmap.girl1) ,
                contentDescription = "小姐姐1",
                modifier = imageModifier,
            )
        }
    }


}