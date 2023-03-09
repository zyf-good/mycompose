package com.zyf.electronicwoodfish


import java.lang.Integer.max
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


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

//反转字符串中的单词 III
 fun reverseWords(s: String): String {
 val strs = s.split(" ")
 val buffer = StringBuffer()
 for (i in strs.indices) {
  buffer.append(StringBuffer(strs[i]).reverse().toString())
  buffer.append(" ")
 }
 return buffer.toString().trim { it <= ' ' }
}

//876. 链表的中间结点
// fun middleNode(head: ListNode?): ListNode? {
//    //快慢指针
// var all : ListNode? = head
// var mid : ListNode? = head
// while(all!=null&&all.next!=null){
//  all = all!!.next.next
//  mid = mid?.next
// }
// return mid
// }

// 19. 删除链表的倒数第 N 个结点
// fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
// //虚拟头节点
// val pre = ListNode(0).apply {
//  this.next = head
// }
// var fastNode: ListNode? = pre
// var slowNode: ListNode? = pre
// for (i in 0..n) {
//  fastNode = fastNode?.next
// }
// while (fastNode != null) {
//  slowNode = slowNode?.next
//  fastNode = fastNode.next
// }
// slowNode?.next = slowNode?.next?.next
// return pre.next
// }

 //3. 无重复字符的最长子串
 fun lengthOfLongestSubstring(str: String): Int {
// 结果
  var result  = 0
  // 右指针
  var right = 0
  // 哈希集合，记录出现的字符
  val chars = hashSetOf<Char>()
  // 遍历字符串中的字符
  str.forEachIndexed { index, _ ->
   // 当索引大于0的时候，执行以下逻辑
   if (index > 0) {
    // 移除一个字符，左指针向右移动一格
    chars.remove(str[index - 1])
   }
   while (right < str.length && !chars.contains(str[right])) {
    // 如果索引小于字符串的长度，同时该字符没出现过，就将该字符放到哈希集合中，然后右指针向右移动一格
    chars.add(str[right])
    right++
   }
   // 计算长度的最大值
   result = kotlin.math.max(result, right - index)
  }
  return result
 }

 //n个数字，求组合数量，和各个组合内容。
 //比如，0，1，2，3，4
 //[0],[0,1],[0,1,2]
 //[0,1,2]和[1,0,2]和[2,1,0]这样的，算重复，不计算在数量内
 fun SummationAnddeDuplication() {
  val long = System.currentTimeMillis()
  val numbers = mutableListOf<Int>()
  for (i in 1..256){
   numbers.add(i)
  }
  val combinations = generateCombinations(numbers)
  println("Combinations:")
  combinations.forEach { println(it) }
  val long1 = System.currentTimeMillis()-long
  println("Number of combinations: ${combinations.size}" +"   time :  ${long1/1000}s" )

 }

 fun generateCombinations(numbers: List<Int>): List<List<Int>> {
  val combinations = mutableListOf<List<Int>>()
  for (i in 0 until numbers.size) {
   val combination = mutableListOf<Int>()
   combination.add(numbers[i])
   combinations.add(combination)
   for (j in i + 1 until numbers.size) {
    combination.add(numbers[j])
    combinations.add(combination.toList())
   }
  }
  return combinations.distinct()
 }

 //567. 字符串的排列
 fun checkInclusion(s1: String, s2: String): Boolean {
  val cnt = s1.groupingBy { it }.eachCount()
  return s2.windowedSequence(s1.length).any { window -> window.groupingBy { it }.eachCount() == cnt }
 }

 //733. 图像渲染
 fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, newColor: Int): Array<IntArray> {
  val oldColor = image[sr][sc]
  // 一样就不需要处理了
  if (oldColor != newColor) {
   dfs(image, sr, sc, oldColor, newColor)
  }
  return image
 }

 private fun dfs(image: Array<IntArray>, sr: Int, sc: Int, oldColor: Int, newColor: Int) {
  // 判断当前位置是否符合要求
  if (
   sr < 0 ||
   sr >= image.size ||
   sc < 0 ||
   sc >= image[sr].size ||
   image[sr][sc] != oldColor
  ) {
   return
  }

  // 修改颜色
  image[sr][sc] = newColor

  // 四个方向
  dfs(image, sr + 1, sc, oldColor, newColor)
  dfs(image, sr - 1, sc, oldColor, newColor)
  dfs(image, sr, sc + 1, oldColor, newColor)
  dfs(image, sr, sc - 1, oldColor, newColor)
 }

 //695. 岛屿的最大面积
 fun maxAreaOfIsland(grid: Array<IntArray>): Int {
  //定义一个表示岛屿的面积
  var max = 0
  //这两个for循环是来遍历整张二维格上的所有陆地的。
  //i 表示行，j表示列
  for (i in grid.indices) {
   for (j in grid[0].indices) {
    //陆地的格
    if (grid[i][j] == 1) {
     //取出最大的面积
     max = max.coerceAtLeast(dfs(grid, i, j))
    }
   }
  }
  //返回最大的陆地面积
  return max
 }

 fun dfs(grid: Array<IntArray>, i: Int, j: Int): Int {
  //当超出岛屿边界（上下左右）的时候，就直接退出，特别要加上当遍历到海洋的时候也要退出，
  if (i < 0 || j < 0 || i >= grid.size || j >= grid[0].size || grid[i][j] == 0) {
   return 0
  }

  //定义一个变量表示岛屿的面积，就是包含几个陆地
  var sum = 1
  //将陆地改为海洋，防止重复陆地重复遍历。
  grid[i][j] = 0
  //遍历上方元素，每遍历一次陆地就加一
  sum += dfs(grid, i + 1, j)
  //遍历下方元素
  sum += dfs(grid, i - 1, j)
  //遍历右边元素
  sum += dfs(grid, i, j + 1)
  //遍历左边元素
  sum += dfs(grid, i, j - 1)
  return sum
 }

 //206. 反转链表
// fun reverseList(head: ListNode?): ListNode? {
//  if (head == null) {
//   return null
//  }
//  var last: ListNode? = null
//  var current = head
//  while (current?.next != null) {
//   val next = current.next
//   current.next = last
//   last = current
//   current = next
//  }
//  current!!.next = last
//  return current
// }

 //21. 合并两个有序链表
//  fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
//  if (l1 == null) {
//   return l2
//  }
//  if (l2 == null) {
//   return l1
//  }
//  return if (l1.`val` < l2.`val`) {
//   l1.next = mergeTwoLists(l1.next, l2)
//   l1
//  } else {
//   l2.next = mergeTwoLists(l1, l2.next)
//   l2
//  }
// }

 //617. 合并二叉树
//  fun mergeTrees(
//  root1: javax.swing.tree.TreeNode?,
//  root2: javax.swing.tree.TreeNode?
// ): javax.swing.tree.TreeNode? {
//  if (root1 == null && root2 == null) return null
//  if (root1 == null) return root2
//  if (root2 == null) return root1
//  root1.`val` += root2.`val`
//  root1.left = mergeTrees(root1.left, root2.left)
//  root1.right = mergeTrees(root1.right, root2.right)
//  return root1
// }


 //77. 组合
 var res = mutableListOf<List<Int>>()
 var list = mutableListOf<Int>()
 fun combine(n: Int, k: Int): List<List<Int>> {
  backtracking(n,1,k)
  return res
 }
 fun backtracking(n:Int, start:Int, k:Int){
  if(list.size==k){
   res.add(list.toMutableList())
   return
  }
  for(i in start .. n){
   list.add(i)
   backtracking(n,i+1,k)
   list.remove(i)
  }
 }

 //46. 全排列
 private lateinit var used:BooleanArray
 fun permute(nums: IntArray): List<List<Int>> {
  val n = nums.size
  used = BooleanArray(n)
  var Size = 1
  for (i in 1..n) {
   Size *= i
  }
  val res = ArrayList<LinkedList<Int>>()
  val temp = LinkedList<Int>()
  backtrack(nums,res,temp)
  return res
 }
 private fun backtrack(nums: IntArray,res: ArrayList<LinkedList<Int>>, temp:LinkedList<Int>):Unit {
  if (temp.size == nums.size) {
   res.add(LinkedList<Int>(temp))
   return
  }
  for (i in 0 until nums.size) {
   if (used[i])continue
   used[i] = true
   temp.add(nums[i])
   backtrack(nums, res, temp)
   used[i] = false
   temp.removeLast()
  }
 }

 //784. 字母大小写全排列(只是觉得太流弊了，实际上还是用上面46. 全排列的解法思路才是正常的)
 fun letterCasePermutation(s: String): List<String> {
  return s.split("").filter{ it.isNotEmpty() }
   .map {
    //println(it)
    if ("\\d".toRegex().matches(it)) listOf(it) else listOf(it.toLowerCase(), it.toUpperCase()) }.fold(
    listOf("")
   ) { p, c -> p.map { v -> c.map { b -> v + b } }.flatten() }
 }

 //70. 爬楼梯
 fun climbStairs(n: Int): Int {
  when (n) {
   1 -> return 1
   2 -> return 2
   3 -> return 3
  }

  // dp思想
  /*
      状态分析：
      集合：dp[i][j]表示从第i个楼梯爬到第j个楼梯的方式
      属性：累加
   */
  val dp = IntArray(n+1)
  dp[0] = 0    // 从第0层楼爬到第0层，没有方法
  dp[1] = 1    // 从第0层爬到第1层，就一种方法
  dp[2] = 2    // 从第0层楼爬到第1层，可以从第1层爬到第2层，也可以从第0层爬到第2层

  /*
      状态计算：
      dp[j] = dp[j-1]+dp[j-2]
   */

  for (j in 3..n) {
   dp[j] = dp[j-1]+dp[j-2]
  }

  return dp[n]
 }




}