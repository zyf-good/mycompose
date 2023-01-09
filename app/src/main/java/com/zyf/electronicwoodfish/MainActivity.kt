package com.zyf.electronicwoodfish


import android.content.Context
import android.inputmethodservice.Keyboard
import android.media.Image
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.zyf.electronicwoodfish.ui.theme.ElectronicWoodfishComposeTheme
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ElectronicWoodfishComposeTheme {
                ProvideWindowInsets{
                    val systemUiController = rememberSystemUiController()
                    SideEffect {
                        systemUiController.setStatusBarColor(Transparent, darkIcons = false)
                    }
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        ScreenPage(this)
                        //Leetcode()
                        //CustomImage()
                    }
                }

            }
        }
    }
}

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
        .background(Yellow)
        .clip( RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
        .aspectRatio(16f / 9f)
        .blur(
            radiusX = 10.dp,
            radiusY = 10.dp,
            edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
        )
    val contentScale =ContentScale.Crop

    val contrast = 2f // 0f..10f (1 should be default)
    val brightness = -180f // -255f..255f (0 should be default)
    val colorMatrix = floatArrayOf(
        contrast, 0f, 0f, 0f, brightness,
        0f, contrast, 0f, 0f, brightness,
        0f, 0f, contrast, 0f, brightness,
        0f, 0f, 0f, 1f, 0f
    )

    Row (
        Modifier.background(White),
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
    Text(text = "返回$answer", color = White, fontSize = 30.sp)
}


@OptIn(ExperimentalMaterialApi::class)
@ExperimentalComposeUiApi
@Composable
fun ScreenPage(context: Context){

    ConstraintLayout {
       val (topLayout,center) =createRefs()

        var showDialog by remember {mutableStateOf(false)}
        var showLoading by remember {mutableStateOf(false)}
        val text = rememberSaveable{
            mutableStateOf(getMeritsText(context))
        }
        val num:Int = getMerits(context)
        val number = rememberSaveable {
            mutableStateOf(num)
        }
        var mMediaPlayer by remember {
           mutableStateOf( MediaPlayer.create(context,R.raw.wooden_fish01))
        }

        val press = rememberSaveable {
            mutableStateOf(false)
        }
        val size  = animateSizeAsState(targetValue = if (press.value) Size(120f, 120f) else Size(150f, 150f) )


        val infiniteTransition = rememberInfiniteTransition()
        val angle by infiniteTransition.animateFloat(
            initialValue = 0F,
            targetValue = 360F,
            animationSpec = infiniteRepeatable(
                animation = tween(2000, easing = LinearEasing)
            )
        )
        val scrollerLazyStata = rememberLazyListState()
        val mp3List :MutableList<Int> = mutableListOf()
        mp3List.add(R.raw.wooden_fish01)
        mp3List.add(R.raw.wooden_fish02)
        mp3List.add(R.raw.wooden_fish03)
        mp3List.add(R.raw.wooden_fish04)
        mp3List.add(R.raw.wooden_fish05)
        mp3List.add(R.raw.wooden_fish06)
        mp3List.add(R.raw.wooden_fish07)
        mp3List.add(R.raw.wooden_fish08)
        mp3List.add(R.raw.wooden_fish09)
        mp3List.add(R.raw.wooden_fish10)
        mp3List.add(R.raw.wooden_fish11)
        mp3List.add(R.raw.wooden_fish12)
        mp3List.add(R.raw.wooden_fish13)
        mp3List.add(R.raw.wooden_fish14)

        var woodFishPic = rememberSaveable {
            mutableStateOf(R.mipmap.wood_fish)
        }
        /*
        * 顶部按钮
        * */
        Column(
            Modifier.constrainAs(topLayout){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ){

            Text(
                "",
                modifier =
                Modifier
                    .height(50.dp)
            )

            Row {
                Image(
                    painter = painterResource(R.mipmap.ic_set_up),
                    contentDescription = "设置",
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 15.dp)
                        .clickable {
                            showDialog = !showDialog
                        }
                )
                Text(
                    text = number.value.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    modifier = Modifier.weight(2f)
                )
                Text(
                    text = "重置",
                    textAlign = TextAlign.Right,
                    fontSize = 18.sp,
                    modifier =
                    Modifier
                        .clickable {
                            number.value = 0
                            saveMerits(number.value.toString(), context, "Merits")
                        }
                        .weight(1f)
                        .padding(end = 15.dp)
                )
            }




        }

        /*
        * 中间的木鱼
        * */
        Column(
            modifier = Modifier.constrainAs(center){
                top.linkTo(topLayout.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

        ) {
            AnimatedVisibility(
                visible = press.value,
                enter =  slideInVertically(
                    initialOffsetY = { fullHeight -> -fullHeight },
                    animationSpec = tween(durationMillis = 250, easing = LinearOutSlowInEasing)
                ) ,
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> -fullHeight },
                    animationSpec = tween(durationMillis = 250, easing = LinearOutSlowInEasing)
                ) + fadeOut()
            ){
                Text(
                    text = text.value,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    modifier = Modifier.width(150.dp)
                    )
            }
            Image(
                painter = painterResource(woodFishPic.value),
                contentDescription = "电子木鱼",
                modifier = Modifier
                    .size(size.value.width.dp, size.value.height.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                press.value = !press.value
                                number.value++
                                saveMerits(number.value.toString(), context, "Merits")
                                mMediaPlayer.start()
                                if (tryAwaitRelease()) {
                                    press.value = !press.value
                                } else {
                                    press.value = !press.value
                                }
                            }
                        )
                    }
            )
        }

        //Dialog
        AnimatedVisibility(
            visible = showDialog
        ){
            Dialog(onDismissRequest = { }, properties = DialogProperties(usePlatformDefaultWidth = false)) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clickable {
                            showDialog = false
                            showLoading = true
                        }) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clickable { }
                            .align(Alignment.BottomCenter)
                            .background(MaterialTheme.colors.background)){
                        Row(Modifier.padding(top = 15.dp, bottom = 15.dp),verticalAlignment = Alignment.Top) {
                            Spacer(Modifier.weight(1f))
                            TextField(value = text.value, onValueChange = {
                                text.value = it
                                saveMerits(it,context,"MeritsText")
                            } )
                            Spacer(Modifier.weight(1f))

                        }
                        Row(Modifier.height(45.dp)) {
                            Image(
                                painter = painterResource(id = R.mipmap.wood_fish),
                                contentDescription = null,
                                modifier = Modifier
                                    .clickable {
                                        woodFishPic.value = R.mipmap.wood_fish
                                    }
                                    .padding(start = 45.dp, end = 45.dp)
                            )
                            Image(
                                painter = painterResource(id = R.mipmap.wooden_fish),
                                contentDescription = null,
                                modifier = Modifier.clickable {
                                    woodFishPic.value = R.mipmap.wooden_fish
                                })
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        LazyRow(Modifier.height(45.dp), state = scrollerLazyStata){
                            items(14){ index ->
                                ListItem(
                                    trailing = { Text(text = "木鱼音效$index ", color = White) },
                                    secondaryText  = { Text(text = "木鱼音效$index ", color = White) },
                                    text  = { Text(text = "木鱼音效$index ", color = White) },
                                    icon = { Icon(painter = painterResource(R.mipmap.wood_fish), contentDescription = "")},
                                    modifier = Modifier.clickable {
                                        mMediaPlayer = MediaPlayer.create(context,
                                            mp3List[index]
                                        )
                                    }
                                )
                            }
                        }
                        Column (    modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                           Button(onClick = {
                               showDialog = false
                               showLoading = true
                           }) {
                               Text(text = "确定")
                           }

                        }
                    }
                }

            }
        }

        //loading
        if (showLoading){
            Timer().schedule(200){
                showLoading = false
            }
            Dialog(onDismissRequest = { }, properties = DialogProperties(usePlatformDefaultWidth = false)) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clickable {
                            showLoading = false
                        }) {
                    Column(
                        Modifier
                            .width(75.dp)
                            .height(75.dp)
                            .clickable { }
                            .align(Alignment.Center)
                           ){
                        Image(
                            painter = painterResource(R.mipmap.icon_loading),
                            contentDescription = "设置",
                            modifier =
                            Modifier
                                .width(75.dp)
                                .height(75.dp)
                                .graphicsLayer {
                                    rotationZ = angle
                                }

                        )
                    }
                }

            }
        }


    }
}

fun getMeritsText(context: Context): String {
    val value: String? = ShareUtil.getString("MeritsText", context)
    if (value.isNullOrEmpty()) {
        ShareUtil.putString("Merits", "0", context)
        return "功德+1"
    }
    return  value
}


fun getMerits(context: Context): Int {
    val value: String? = ShareUtil.getString("Merits", context)
    if (value.isNullOrEmpty()) {
        ShareUtil.putString("Merits", "0", context)
        return 0
    }
    return  value.toInt()
}


fun saveMerits(number: String,context: Context,key : String){
    ShareUtil.putString(key, number, context)
}



