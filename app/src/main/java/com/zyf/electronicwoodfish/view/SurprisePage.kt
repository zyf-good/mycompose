package com.zyf.electronicwoodfish.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zyf.electronicwoodfish.R
import com.zyf.electronicwoodfish.nav.NavController
import com.zyf.electronicwoodfish.nav.RouterNameUrls
import com.zyf.electronicwoodfish.nav.RouterUrls
import com.zyf.electronicwoodfish.ui.theme.Black
import com.zyf.electronicwoodfish.util.cdp


/**
 * @author zengyifeng
 * @date createDate:2023-03-22
 * @brief description
 */


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SurprisePage() {

    Column(){
        Text(
            "",
            modifier =
            Modifier
                .height(60.cdp)
        )
        val lazyState = rememberLazyListState()

        LazyColumn(modifier = Modifier.padding(10.cdp), state = lazyState) {

            val list = mutableListOf<String>()
            list.add(RouterUrls.XMLVIEW)
            list.add(RouterUrls.CUSTOMIMAGE)
            list.add(RouterUrls.NETWORKIMAGE)
            list.add(RouterUrls.LEETCODE)

            val nameList = mutableListOf<String>()
            nameList.add(RouterNameUrls.XMLVIEW)
            nameList.add(RouterNameUrls.CUSTOMIMAGE)
            nameList.add(RouterNameUrls.NETWORKIMAGE)
            nameList.add(RouterNameUrls.LEETCODE)

            items(4) { index ->
                //获取到对应的本地图标地址id
                val resID = LocalContext.current.resources
                    .getIdentifier(list[index], "mipmap", LocalContext.current.packageName)
                ListItem(
                    text = { Text(text = nameList[index]) },
                    icon = { Image(painter = painterResource(if (resID!=0){
                        resID
                    }else{
                        R.mipmap.close_button
                    }) , contentDescription = "") },
                    modifier = Modifier.clickable {
                        NavController.instance.navigate(list[index])
                    }
                )

            }

        }
    }


}