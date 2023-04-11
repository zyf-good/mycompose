package com.zyf.electronicwoodfish.view


import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.zyf.electronicwoodfish.R

/**
 * @author zengyifeng
 * @date createDate:2023-03-21
 * @brief description 附带效应 DisposableEffect
 */

@Preview(showBackground = true ,showSystemUi = true)
@Composable
fun DisposableEffectView(){
    Row (
        Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val context = LocalContext.current
        val webView : WebView  =  WebView(context).apply {
            id = R.id.webView
        }


        AndroidView(factory = { context ->
            webView
        },
            modifier = Modifier.fillMaxSize(),
            update = { view ->

            })


        val lifecycle = LocalLifecycleOwner.current.lifecycle
        DisposableEffect(key1 = lifecycle, key2 = webView) {
            val lifecycleObserver = getWebViewLifecycleObserver(webView)
            lifecycle.addObserver(lifecycleObserver)
            onDispose {
                lifecycle.removeObserver(lifecycleObserver)
            }
        }


    }

}

fun getWebViewLifecycleObserver(webView: WebView): LifecycleEventObserver =
    LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE ->
            {
                Log.w("webView", "ON_CREATE ", )
                webView.loadUrl("https://blog.csdn.net/shop_and_sleep?spm=1010.2135.3001.5343")
            }
            Lifecycle.Event.ON_START -> {
                Log.w("webView", "ON_START ", )
            }
            Lifecycle.Event.ON_RESUME ->{
                Log.w("webView", "ON_RESUME ", )
                webView.onResume()}
            Lifecycle.Event.ON_PAUSE ->
            {
                Log.w("webView", "ON_PAUSE ", )
                webView.onPause()
            }
            Lifecycle.Event.ON_STOP -> {
                Log.w("webView", "ON_STOP ", )
            }
            Lifecycle.Event.ON_DESTROY ->
            {
                Log.w("webView", "ON_DESTROY ", )
                webView.destroy()
            }
            else -> throw IllegalStateException()
        }
    }