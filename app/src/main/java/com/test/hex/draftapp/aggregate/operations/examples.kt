package com.test.hex.draftapp.aggregate.operations

import android.util.Log
import com.test.hex.draftapp.doDefferedSum

private const val AGG = "agg"

private val list = listOf(1, 2, 3, 4, 5, 6)

fun logAllAggregateOperations() {
    Log.d(AGG, "assertTrue(list.any { it % 2 == 0 }) : ${
        assertTrue(list.any { it % 2 == 0 })
    }")
    Log.d(AGG, "assertFalse(list.any { it > 10 }) : ${
        assertFalse(list.any { it > 10 })
    }")
    Log.d(AGG, "assertTrue(list.all { it < 10 }) : ${
        assertTrue(list.all { it < 10 })
    }")
    Log.d(AGG, "assertFalse(list.all { it % 2 == 0 }) : ${
        assertFalse(list.all { it % 2 == 0 })
    }")
    Log.d(AGG, "assertFalse(list.all { it % 2 == 0 }) : ${
        assertFalse(list.all { it % 2 == 0 })
    }")
    Log.d(AGG, "assertEquals(3, list.count { it % 2 == 0 }) : ${
        assertEquals(3, list.count { it % 2 == 0 })
    }")
    Log.d(AGG, "assertEquals(25, list.fold(4) { total, next -> total + next }) : ${
        assertEquals(25, list.fold(4) { total, next -> total + next })
    }")
    Log.d(AGG, "assertEquals(25, list.foldRight(4) { total, next -> total + next }) : ${
        assertEquals(25, list.foldRight(4) { total, next -> total + next })
    }")
    Log.d(AGG, "assertEquals(6, list.max()) : ${
        assertEquals(6, list.max()!!)
    }")
    Log.d(AGG, "assertEquals(1, list.maxBy { -it }) : ${
        assertEquals(1, list.maxBy { -it }!!)
    }")
    Log.d(AGG, "assertEquals(1, list.min()) : ${
        assertEquals(1, list.min()!!)
    }")
    Log.d(AGG, "assertEquals(6, list.minBy { -it }) : ${
        assertEquals(6, list.minBy { -it }!!)
    }")
    Log.d(AGG, "assertTrue(list.none { it % 7 == 0 }) : ${
        assertTrue(list.none { it % 7 == 0 })
    }")
    Log.d(AGG, "assertEquals(21, list.reduce { total, next -> total + next }) : ${
        assertEquals(21, list.reduce { total, next -> total + next })
    }")
    Log.d(AGG, "assertEquals(21, list.reduceRight { total, next -> total + next }) : ${
        assertEquals(21, list.reduceRight { total, next -> total + next })
    }")
    Log.d(AGG, "assertEquals(3, list.sumBy { it % 2 }) : ${
        assertEquals(3, list.sumBy { it % 2 })
    }")
    Log.d(AGG, "assertEquals(listOf(5, 6), list.drop(4)) : ${
        assertEquals(listOf(5, 6), list.drop(4))
    }")
    Log.d(AGG, "assertEquals(listOf(3, 4, 5, 6), list.dropWhile { it < 3}) : ${
        assertEquals(listOf(3, 4, 5, 6), list.dropWhile { it < 3})
    }")
    Log.d(AGG, "assertEquals(listOf(1, 2, 3, 4), list.dropLastWhile { it > 4}) : ${
        assertEquals(listOf(1, 2, 3, 4), list.dropLastWhile { it > 4})
    }")
    Log.d(AGG, "assertEquals(listOf(2, 4, 6), list.filter { it % 2 == 0}) : ${
        assertEquals(listOf(2, 4, 6), list.filter { it % 2 == 0})
    }")
    Log.d(AGG, "assertEquals(listOf(1, 3, 5), list.filterNot { it % 2 == 0}) : ${
        assertEquals(listOf(1, 3, 5), list.filterNot { it % 2 == 0})
    }")
    Log.d(AGG, "assertEquals(listOf(1, 2, 3, 4), (listOf(1, 2, 3, 4, null, null)).filterNotNull()) : ${
        assertEquals(listOf(1, 2, 3, 4), (listOf(1, 2, 3, 4, null, null)).filterNotNull())
    }")
    Log.d(AGG, "assertEquals(listOf(2, 4, 5), list.slice(listOf(1, 3, 4))) : ${
        assertEquals(listOf(2, 4, 5), list.slice(listOf(1, 3, 4)))
    }")
    Log.d(AGG, "assertEquals(listOf(2, 4, 5), list.slice(listOf(1, 3, 4))) : ${
        assertEquals(listOf(1, 2), list.take(2))
    }")
    Log.d(AGG, "assertEquals(listOf(5, 6), list.takeLast(2)) : ${
        assertEquals(listOf(5, 6), list.takeLast(2))
    }")
    Log.d(AGG, "assertEquals(listOf(1, 2), list.takeWhile { it < 3 }) : ${
        assertEquals(listOf(1, 2), list.takeWhile { it < 3 })
    }")
    Log.d(AGG, "assertEquals(listOf(1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7),\n" +
            "                list.flatMap { listOf(it, it + 1) }) : ${
        assertEquals(listOf(1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7),
                list.flatMap { listOf(it, it + 1) })
    }")
    Log.d(AGG, "assertEquals(mapOf(\"odd\" to listOf(1, 3, 5), \"even\" to listOf(2, 4, 6)),\n" +
            "                list.groupBy { if (it % 2 == 0) \"even\" else \"odd\" }) : ${
        assertEquals(mapOf("odd" to listOf(1, 3, 5), "even" to listOf(2, 4, 6)),
                list.groupBy { if (it % 2 == 0) "even" else "odd" })
    }")
    Log.d(AGG, "assertEquals(listOf(2, 4, 6, 8, 10, 12), list.map { it * 2 }) : ${
        assertEquals(listOf(2, 4, 6, 8, 10, 12), list.map { it * 2 })
    }")
    Log.d(AGG, "assertEquals(listOf(0, 2, 6, 12, 20, 30), list.mapIndexed { index, it -> index * it }) : ${
        assertEquals(listOf(0, 2, 6, 12, 20, 30), list.mapIndexed { index, it -> index * it })
    }")
    Log.d(AGG, "assertEquals(listOf(2, 4, 6, 8), (listOf(1, 2, 3, 4, null, null)).mapNotNull { it!! * 2 }) : ${
        assertEquals(listOf(2, 4, 6, 8), (listOf(1, 2, 3, 4, null, null)).mapNotNull { it?.times(2) })
    }")
    Log.d(AGG, "assertTrue(list.contains(2)) : ${
        assertTrue(list.contains(2))
    }")
    Log.d(AGG, "assertEquals(2, list.elementAt(1)) : ${
        assertEquals(2, list.elementAt(1))
    }")
    Log.d(AGG, "assertEquals(20, list.elementAtOrElse(10) { 2 * it }) : ${
        assertEquals(20, list.elementAtOrElse(10) { 2 * it })
    }")
    Log.d(AGG, "assertNull(list.elementAtOrNull(10)) : ${
        assertNull(list.elementAtOrNull(10))
    }")
    Log.d(AGG, "assertEquals(2, list.first { it % 2 == 0 }) : ${
        assertEquals(2, list.first { it % 2 == 0 })
    }")
    Log.d(AGG, "assertNull(list.firstOrNull { it % 7 == 0 }) : ${
        assertNull(list.firstOrNull { it % 7 == 0 })
    }")
    Log.d(AGG, "assertEquals(3, list.indexOf(4)) : ${
        assertEquals(3, list.indexOf(4))
    }")
    Log.d(AGG, "assertEquals(1, list.indexOfFirst { it % 2 == 0 }) : ${
        assertEquals(1, list.indexOfFirst { it % 2 == 0 })
    }")
    Log.d(AGG, "assertEquals(5, list.indexOfLast { it % 2 == 0 }) : ${
        assertEquals(5, list.indexOfLast { it % 2 == 0 })
    }")
    Log.d(AGG, "assertEquals(6, list.last { it % 2 == 0 }) : ${
        assertEquals(6, list.last { it % 2 == 0 })
    }")
    Log.d(AGG, "assertEquals(5, (listOf(2, 2, 3, 4, 5, 5, 6)).lastIndexOf(5)) : ${
        assertEquals(5, (listOf(2, 2, 3, 4, 5, 5, 6)).lastIndexOf(5))
    }")
    Log.d(AGG, "assertNull(listOf(1, 2, 3, 4, 5, 6).lastOrNull { it % 7 == 0 }) : ${
        assertNull(listOf(1, 2, 3, 4, 5, 6).lastOrNull { it % 7 == 0 })
    }")
    Log.d(AGG, "assertEquals(5, list.single { it % 5 == 0 }) : ${
        assertEquals(5, list.single { it % 5 == 0 })
    }")
    Log.d(AGG, "assertNull(list.singleOrNull { it % 7 == 0 }) : ${
        assertNull(list.singleOrNull { it % 7 == 0 })
    }")
//    Log.d(AGG, "assertEquals(listOf(3, 4, 6, 8, 10, 11), (listOf(1, 2, 3, 4, 5, 6)).merge(listOf(2, 2, 3, 4, 5, 5, 6)){ it1, it2 -> it1 + it2 }) : ${
//        assertEquals(listOf(3, 4, 6, 8, 10, 11), (listOf(1, 2, 3, 4, 5, 6)).merge(listOf(2, 2, 3, 4, 5, 5, 6)){ it1, it2 -> it1 + it2 })
//    }")
    Log.d(AGG, "assertEquals(Pair(listOf(2, 4, 6), listOf(1, 3, 5)), list.partition { it % 2 == 0 }) : ${
        assertEquals(Pair(listOf(2, 4, 6), listOf(1, 3, 5)), list.partition { it % 2 == 0 })
    }")
    Log.d(AGG, "assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8), list + listOf(7, 8)) : ${
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8), list + listOf(7, 8))
    }")
    Log.d(AGG, "assertEquals(listOf(Pair(1, 7), Pair(2, 8)), list.zip(listOf(7, 8))) : ${
        assertEquals(listOf(Pair(1, 7), Pair(2, 8)), list.zip(listOf(7, 8)))
    }")
    Log.d(AGG, "assertEquals(Pair(listOf(5, 6), listOf(7, 8)), listOf(Pair(5, 7), Pair(6, 8)).unzip()) : ${
        assertEquals(Pair(listOf(5, 6), listOf(7, 8)), listOf(Pair(5, 7), Pair(6, 8)).unzip())
    }")
    Log.d(AGG, "assertEquals(mutableListOf(5, 7, 2, 3), mutableListOf(3, 2, 7, 5).reverse()) : ${
        assertEquals(mutableListOf(5, 7, 2, 3), mutableListOf(3, 2, 7, 5).apply { reverse() })
    }")
    Log.d(AGG, "assertEquals(listOf(5, 7, 2, 3), listOf(3, 2, 7, 5).reversed()) : ${
        assertEquals(listOf(5, 7, 2, 3), listOf(3, 2, 7, 5).reversed())
    }")
    Log.d(AGG, "assertEquals(mutableListOf(2, 3, 5, 7), mutableListOf(3, 2, 7, 5).sort()) : ${
        assertEquals(mutableListOf(2, 3, 5, 7), mutableListOf(3, 2, 7, 5).apply { sort() })
    }")
    Log.d(AGG, "assertEquals(mutableListOf(3, 7, 2, 5), mutableListOf(3, 2, 7, 5).sortBy { it % 3 }) : ${
        assertEquals(mutableListOf(3, 7, 2, 5), mutableListOf(3, 2, 7, 5).apply { sortBy { it % 3 } })
    }")
    Log.d(AGG, "assertEquals(mutableListOf(7, 5, 3, 2), mutableListOf(3, 2, 7, 5).sortDescending()) : ${
        assertEquals(mutableListOf(7, 5, 3, 2), mutableListOf(3, 2, 7, 5).apply { sortDescending() })
    }")
    Log.d(AGG, "assertEquals(listOf(2, 5, 7, 3), listOf(3, 2, 7, 5).sortedByDescending { it % 3 }) : ${
        assertEquals(listOf(2, 5, 7, 3), listOf(3, 2, 7, 5).sortedByDescending { it % 3 })
    }")
//    doDefferedSum() //1784293664
}

fun <T> assertEquals(list: List<T>, zip: List<T>): Boolean {
    list.forEachIndexed { index, _ ->
        assertEquals(list[index], zip[index])
    }
    return true
}

fun <T> assertEquals(pair1: Pair<T, T>, pair2: Pair<T, T>): Boolean {
    assertEquals(pair1.first, pair2.first)
    assertEquals(pair1.second, pair2.second)
    return true
}

fun <T, E> assertEquals(map1: Map<T, E>, map2: Map<T, E>): Boolean {
    assertEquals(map1.size, map2.size)
    for (key in map1.keys) {
        assertEquals(map1[key], map2[key])
    }
    return true
}

fun assertTrue(boolean: Boolean): Boolean {
    if (!boolean) throw AssertionError("Assertion failed")
    return true
}

fun assertFalse(boolean: Boolean): Boolean {
    if (boolean) throw AssertionError("Assertion failed")
    return true
}

fun <T> assertNull(a: T): Boolean {
    if (a != null) throw AssertionError("Assertion failed")
    return true
}

fun assertEquals(a: Collection<Int>?, b: Collection<Int>?): Boolean {
    if (b?.toIntArray()?.let { a?.toIntArray()?.contentEquals(it) } != true) throw AssertionError("Assertion failed")
    return true
}

fun assertEquals(a: Int, b: Int): Boolean {
    if (a != b) throw AssertionError("Assertion failed")
    return true
}

fun <T> assertEquals(a: T, b: T): Boolean {
    if (a != b || a == null || b == null) throw AssertionError("Assertion failed")
    return true
}
