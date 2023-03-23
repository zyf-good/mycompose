package com.zyf.electronicwoodfish.util

import android.content.Context
import android.content.SharedPreferences


/**
 * @author zengyifeng
 * @date createDate:2022-11-01
 * @brief description
 */
object ShareUtil {
 private var sps: SharedPreferences?=null
 private fun getSps(context: Context):SharedPreferences{
  if(sps ==null){
   sps =context.getSharedPreferences("default",Context.MODE_MULTI_PROCESS)
  }
  return sps!!
 }
 fun putString(key:String,value:String?,context:Context){
  if(!value.isNullOrBlank()){
   val editor:SharedPreferences.Editor= getSps(context).edit()
   editor.putString(key,value)
   editor.apply()
  }
 }
 fun getString(key:String,context:Context):String?{
  if(key.isNotBlank()){
   val sps:SharedPreferences= getSps(context)
   return sps.getString(key,null)
  }
  return null
 }
}