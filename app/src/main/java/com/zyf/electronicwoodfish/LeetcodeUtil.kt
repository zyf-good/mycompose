package com.zyf.electronicwoodfish

import androidx.compose.ui.input.key.Key.Companion.J
import androidx.constraintlayout.compose.DesignElements.map
import java.lang.Integer.max
import java.lang.Integer.sum
import java.util.*


/**
 * @author zengyifeng
 * @date createDate:2022-11-05
 * @brief description
 */
object LeetcodeUtil {

 /*
 * 1480. 一维数组的动态和
 * */
 fun runningSum(nums: IntArray): IntArray {
  var nums2 = IntArray(nums.size)
  for(i in nums.indices){
   if (i==0){
    nums2[i] = nums[i]
   }else if (i>0){
    nums2[i] = nums2[i-1]+nums[i]
   }
  }
  return nums2

 }

 /*
 * 383. 赎金信
 * */
 fun canConstruct(ransomNote: String, magazine: String): Boolean {
  //记录杂志字符串出现的次数,26个字母
  val arr = IntArray(26)
  var temp: Int
  //每在magazine找到一个元素就将其对应的数量+1
  for (element in magazine) {
   //ASCII码中a为97，这里element如果为b ，意思其实就是temp= 98-97 ，为1，arr中的第二个代表着b的+1了
   temp = element - 'a'
   arr[temp]++
  }
  //每在ransomNote找到一个元素就将其对应的数量+1
  for (element in ransomNote) {
   temp = element - 'a'
   //对于金信中的每一个字符都在数组中查找
   //找到相应位减一，否则找不到返回false
   if (arr[temp] > 0) {
    arr[temp]--
   } else {
    return false
   }
  }
  return true
 }

 //1672. 最富有客户的资产总量
  fun maximumWealth(accounts: Array<IntArray>): Int {
    var maxInt = 0
    for (account in accounts){
     maxInt = max(maxInt,account.sum())
    }
    return maxInt
  }

 //704. 二分查找
 fun search(nums: IntArray, target: Int): Int {
//暴力解法
//   for ( i in nums.indices){
//      if (nums[i] == target ){
//       return i;
//      }
//   }
//  return -1
  var left = 0
  var right = nums.size - 1
  var index = -1

  while (left <= right) {
   val middle = left + (right - left) / 2
   if (nums[middle] > target) {
    // target 比中间值小，说明位于数组的左侧
    right = middle -1
   } else if (nums[middle] < target) {
    // target 比中间值大，说明位于数组的右侧
    left = middle + 1
   } else {
    index = middle
    break
   }
  }
  return index
 }

 //278. 第一个错误的版本
// fun firstBadVersion(n: Int) : Int {
//  var left = 1
//  var right = n
//  while(left<right){
//   val mid = left + (right-left)/2
//   if(isBadVersion(mid)){
//    right = mid
//   }else{
//    left =mid+1
//   }
//  }
//  return right
// }

//977. 有序数组的平方
 fun sortedSquares(nums: IntArray): IntArray {
  val newIntArray = mutableListOf<Int>();
    for (i in nums){
     val a = i*i
     newIntArray.add(a)
    }
    newIntArray.sort()
   return  newIntArray.toIntArray()
 //巧妙利用map,但是更耗性能
 // return nums.map { it * it }.sorted().toIntArray()
 }

// 189. 轮转数组
 //暴力解决，看不懂优化算法
 fun rotate(nums: IntArray, k: Int): IntArray {
 for (i in 0 until k){
  for (j in 1 until nums.size) {
   val intI = nums[0]
   val intJ = nums[j]
   nums[0] = intJ
   nums[j] = intI
 }
 }
 return nums
 }

 //283. 移动零
 fun moveZeroes(nums: IntArray): Unit {
   var j =0
   for (i in 0 until nums.size){
     if (nums[i]!=0){
       nums[j++]= nums[i]
     }
  }
   while (j<nums.size){
    nums[j++] = 0
  }
 }

//167. 两数之和 II - 输入有序数组
 fun twoSum(numbers: IntArray, target: Int): IntArray {
 var i =0
 var j =numbers.size-1
 while (i<j){
  val sum = numbers[i]+numbers[j]
  if (sum>target){
    j--
  }else if(sum<target){
   i++
  }else{
   return arrayOf(i+1,j+1).toIntArray()
  }

 }
 return arrayOf(-1,-1).toIntArray()
 }

//344. 反转字符串
 fun reverseString(s: CharArray): Unit {
 val lastIndex = s.size-1
 var left = 0
  if (lastIndex%2==1){
    left = (lastIndex-1)/2
  }else{
   left = lastIndex/2
  }
 for (i in 0..left){
  val a = s[i]
  val b = s[lastIndex-i]
  s[lastIndex-i] = a
  s[i] = b
 }
 }

// 2032. 至少在两个数组中出现的值
 fun twoOutOfThree(nums1: IntArray, nums2: IntArray, nums3: IntArray): List<Int> {
     val map = HashMap<Int,Int>()
 val list = mutableListOf<Int>()
     for (item1 in nums1){
      map[item1] = 1
     }

 for (item2 in nums2){
  if (map.containsKey(item2)){
   map[item2] =  map[item2]!! +1
  }else{
   map[item2] = 1
  }
 }

 for (item3 in nums3){
  if (map.containsKey(item3)){
   map[item3] = map[item3]!! +1
  }else{
   map[item3] = 1
  }
 }

for (v in map.keys){
    if (map[v]!!-1 != 0){
     list.add(v)
    }
}

 return list

 }

 //35. 搜索插入位置
 fun searchInsert(nums: IntArray, target: Int): Int {
  var left = 0
  var right = nums.size - 1
  var index = -1

  while (left <= right) {
   val middle = left + (right - left) / 2
   if (nums[middle] > target) {
    // target 比中间值小，说明位于数组的左侧
    right = middle -1
   } else if (nums[middle] < target) {
    // target 比中间值大，说明位于数组的右侧
    left = middle + 1
   } else {
    index = middle
    break
   }
  }

  //等于-1及数组中没有target，需要插入
  if(index==-1){
   index = left
  }
  return index
 }




}