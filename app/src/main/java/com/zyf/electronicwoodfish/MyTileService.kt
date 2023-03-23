package com.zyf.electronicwoodfish

import android.content.Intent
import android.graphics.drawable.Icon
import android.os.IBinder
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.util.Log
import com.zyf.electronicwoodfish.view.getMerits
import com.zyf.electronicwoodfish.view.saveMerits
import java.util.*
import kotlin.math.log

/**
 * @author zengyifeng
 * @date createDate:2023-03-22
 * @brief description
 */
class MyTileService : TileService() {
    private val TAG = "MyTileService"
    private var timer: Timer? = null

    override fun onTileAdded() {
        super.onTileAdded()
        Log.d(TAG, "onTileAdded: ")
    }

    override fun onStartListening() {
        super.onStartListening()
        checkQsTile()
        Log.d(TAG, "onStartListening: ")
    }

    override fun onStopListening() {
        Log.d(TAG, "onStopListening: ")
        super.onStopListening()
    }

    override fun onClick() {
        Log.d(TAG, "onClick: ")
        super.onClick()
        updateQsTile()
        checkQsTile()
    }

    override fun onTileRemoved() {
        Log.d(TAG, "onTileRemoved: ")
        super.onTileRemoved()
        timer?.cancel()
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return super.onBind(intent)
        Log.d(TAG, "onBind: ")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
        Log.d(TAG, "onUnbind: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    private fun updateQsTile() {
        if (qsTile.state === Tile.STATE_ACTIVE) {
            qsTile.label = "自动敲木鱼"
            qsTile.icon = Icon.createWithResource(this, R.mipmap.wood_fish)
            qsTile.state = Tile.STATE_INACTIVE
        } else {
            //active
            qsTile.label = "正在自动敲木鱼"
            qsTile.icon = Icon.createWithResource(this, R.mipmap.wood_fish)
            qsTile.state = Tile.STATE_ACTIVE
        }
        qsTile.updateTile()
    }

    private fun checkQsTile(){
        if (qsTile.state === Tile.STATE_ACTIVE) {

            if (timer==null){
                timer = Timer()
                timer!!.scheduleAtFixedRate(object : TimerTask() {
                    override fun run() {
                        // 执行需要执行的操作
                        var num = getMerits(this@MyTileService)
                        num++
                        saveMerits(num.toString(), this@MyTileService, "Merits")
                        Log.d(TAG, "run: 正在自动敲木鱼$num")
                    }
                }, 0, 5000)
            }
        } else {
            timer?.cancel()
        }
    }


}