import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

suspend fun main() = coroutineScope {
    val persons = listOf(
        Person("Андрей", 27),
        Person("Сергей", 34),
        Person("Дмитрий", 26),
        Person("Светлана", 29),
        Person("Виктория", 33)
    )
    val weatherList = listOf(
        Weather("Москва", "Облачно с прояснениями", 18),
        Weather("Санкт-Петербург", "Облачно", 17),
        Weather("Челябинск", "Ясно", 21),
        Weather("Самара", "Ясно", 22),
        Weather("Новосибирск", "Дождь", 14)
    )
    val randoms = List(10) { Random.nextInt(0, 100) }
    launch {
        downloadData(persons)
        downloadData(weatherList)
        downloadData(randoms)
        println("Данные загружены")
        println(persons)
        println(weatherList)
        println(randoms)
    }.join()
    println("Программа завершена")
}

suspend fun <T> downloadData(list: List<T>): List<T> {
    list.forEach {
        delay(1000L)
        println("Элемент \"$it\" загружен")
    }
    return list
}

data class Person(val name: String, val age: Int)

data class Weather(val city: String, val description: String, val temp: Int)