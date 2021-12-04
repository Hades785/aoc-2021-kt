class Day03Solver : TaskSolver {
    override fun solvePart1(input: List<String>): String {
        val gamma = input.map { it.toCharArray().map(Char::toString).map(String::toInt) }
            .transpose()
            .map { if (it.sum() > it.size / 2) 1 else 0 }

        val epsilon = gamma.map { if (it == 1) 0 else 1 }

        return (gamma.joinToString("").toInt(2) * epsilon.joinToString("").toInt(2)).toString()
    }

    override fun solvePart2(input: List<String>): String {
        val o2 = filter(input, false)
        val co2 = filter(input, true)

        return (o2.toInt(2) * co2.toInt(2)).toString()
    }

    private fun filter(input: List<String>, invert: Boolean, index: Int = 0): String {
        val mostCommon = input.map { it.toCharArray().map(Char::toString).map(String::toInt) }
            .transpose()
            .map { if (it.sum() >= it.size / 2f) 1 else 0 }[index]
        val filter = if (invert) mostCommon.inv() + 2 else mostCommon
        val filtered = input.filter { it[index] == filter.toString().toCharArray().first() }
        return if (filtered.size > 1) filter(filtered, invert, index + 1) else filtered.first()
    }
}

private fun <T> List<List<T>>.transpose(): List<List<T>> {
    val list = MutableList<MutableList<T>>(get(0).size) { mutableListOf() }

    forEach {
        it.forEachIndexed { idx, t -> list[idx].add(t) }
    }

    return list
}

fun main() {
    println("Day 03")

    check(runPart1<Day03Solver>("day03-test").toInt() == 198)
    println("Part 1: ${runPart1<Day03Solver>("day03-input")}")

    check(runPart2<Day03Solver>("day03-test").toInt() == 230)
    println("Part 2: ${runPart2<Day03Solver>("day03-input")}")
}