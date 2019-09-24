package knf.tools.kprobability

import android.util.Log
import kotlin.random.Random

class Probabilities<T : Any>(private val list: List<Pair<Double, T>>) {

    fun random(): T {
        return findItem(list)
    }

    private fun findItem(list: List<Pair<Double, T>>): T {
        var randomProb = Random.nextDouble(100.0)
        list.shuffled().forEach {
            randomProb -= it.first
            if (randomProb <= 0)
                return it.second
        }
        return findItem(list)
    }

    class Builder<T : Any>(private val default: T? = null) {
        private var tSum = 0.0
        private var list: MutableList<Pair<Double, T>> = mutableListOf()

        fun add(item: T, probability: Double) {
            tSum += probability
            list.add(Pair(probability, item))
        }

        fun build(): Probabilities<T> {
            check(list.isNotEmpty() || default != null) { "No items added" }
            var sum = 0.0
            list.forEach { sum += it.first }
            check(sum <= 100.0) { "The probability sum of all items needs to be equal or lower to 100%" }
            if (default != null && sum == 100.0) Log.w(
                "KProbability",
                "Warning: Items sum is equal to 100%, the default item will have 0% probability",
                IllegalStateException()
            )
            if (default != null) {
                add(default, 100.0 - sum)
            } else if (sum < 100.0) {
                val nList: MutableList<Pair<Double, T>> = mutableListOf()
                list.forEach {
                    val nProb = (it.first * 100.0) / sum
                    nList.add(Pair(nProb, it.second))
                }
                list = nList
            }
            return Probabilities(list)
        }
    }

}