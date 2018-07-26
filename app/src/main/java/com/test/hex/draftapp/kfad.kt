package com.test.hex.draftapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder

/** kfad - Kotlin for Android Developers */

/** sqlite mapping */
class SomeClass(var map: MutableMap<String, Any?>) {
    var _id: Long by map
    var date: Long by map
    var description: String by map
    var high: Int by map
    var low: Int by map
    var iconUrl: String by map
    var cityId: Long by map

    constructor(date: Long, description: String, high: Int, low: Int,
                iconUrl: String, cityId: Long) : this(HashMap()) {
        this.date = date
        this.description = description
        this.high = high
        this.low = low
        this.iconUrl = iconUrl
        this.cityId = cityId
    }
}

/** parse objects from db */
fun <T : Any> SelectQueryBuilder.parseList(
        parser : (Map<String, Any?>) -> T): List<T> =
        parseList(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any?>): T
                    = parser(columns)
        })

fun <T : Any> SelectQueryBuilder.parseOpt(
        parser : (Map<String, Any?>) -> T): T? =
        parseOpt(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any?>): T
                    = parser(columns)
        })

/** transforming the map to a vararg array */
fun <K, V : Any> MutableMap<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
        map{ Pair(it.key, it.value!!) }.toTypedArray()
/*
    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)
        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }
*/

val x = try { doSomething() } catch(e: Exception) { null }
fun doSomething() {
    //...
}

/** coroutines */
fun doDefferedSum() {
    val deffered = (1..1_000_000).map { n ->
        async {
            n
        }
    }
    runBlocking {
        val sum = deffered .sumBy { it.await() }
        Log.d("kfad", "Sum: $sum") //1784293664
    }
}

/** return class.java by class */
/*
"029" -> {
   intention = Intent(this, javaClass<L029>())
   startActivity(intention)
}
*/

//inline fun <reified T: Activity> javaClass() = T::class.java

//inline fun <reified T: Activity> Context.startActivity() {
//    val intent = Intent(this, javaClass<T>())
//    startActivity(intent)
//}

inline fun <reified T: Activity> Context.startActivity(vararg params: Pair<String, String>) {
    val intent = Intent(this, T::class.java)
    params.forEach { intent.putExtra(it.first, it.second) }
    startActivity(intent)
}
/** inline fun <T, R> T.let(f: (T) -> R): R = f(this) */
/*  forecast?.let { dataMapper.convertDayToDomain(it) } */

/** inline fun <T, R> with(receiver: T, f: T.() -> R): R = receiver.f() */
/*  fun convertFromDomain(forecast: ForecastList) = with(forecast) {
    val daily = dailyForecast.map { convertDayFromDomain(id, it) }
    CityForecast(id, city, country, daily)
    } */

/** inline fun <T> T.apply(f: T.() -> Unit): T { f(); return this } */
/*  val textView = TextView(context).apply {
    text = "Hello"
    hint = "Hint"
    textColor = android.R.color.white
    } */

/*
doAsync {
    val result = RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute()
    uiThread { bindForecast(result) }
}
*/
//    |
//    v
/*
async(UI) {
    val result = bg { RequestDayForecastCommand(intent.getLongExtra(ID, -1))
            .execute() }
    bindForecast(result.await())
}
*/
