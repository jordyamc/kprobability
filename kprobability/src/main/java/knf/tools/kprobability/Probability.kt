package knf.tools.kprobability

fun <T : Any> probabilityOf(
    default: T? = null,
    constructor: Probabilities.Builder<T>.() -> Unit
): Probabilities<T> {
    val probabilities = Probabilities.Builder(default)
    constructor(probabilities)
    return probabilities.build()
}

fun <T : Any> Probabilities.Builder<T>.item(item: T, probability: Double) {
    check(probability > 0 && probability < 100) { "The probability needs to be between 1 and 99 %" }
    add(item, probability)
}

infix fun Int.timesIn(int: Int): Double {
    check(this < int) { "The first number needs to be lower than second." }
    check(this > 0) { }
    return (this * 100.0) / int
}

val Int.percent: Double
    get() {
        check(this < 100) { "The item need to be less tan 100%" }
        return this.toDouble()
    }