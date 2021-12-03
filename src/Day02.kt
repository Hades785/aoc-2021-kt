class Day02Solver : TaskSolver {
    override fun solvePart1(input: List<String>): String =
        input.fold(Pair(0, 0)) { vector: Pair<Int, Int>, inputPart: String ->
            val (direction, value) = inputPart.split(" ")
            vector.run {
                when (direction) {
                    "forward" -> copy(first = first + value.toInt())
                    "up" -> copy(second = second - value.toInt())
                    "down" -> copy(second = second + value.toInt())
                    else -> copy()
                }
            }
        }.run { first * second }.toString()

    override fun solvePart2(input: List<String>): String =
        input.fold(Pair(Pair(0, 0), 0)) { data: Pair<Pair<Int, Int>, Int>, inputPart: String ->
            val (direction, value) = inputPart.split(" ")
            data.run {
                when (direction) {
                    "forward" -> copy(first = first.let {
                        it.copy(
                            first = it.first + value.toInt(),
                            second = it.second + second * value.toInt()
                        )
                    })
                    "up" -> copy(second = second - value.toInt())
                    "down" -> copy(second = second + value.toInt())
                    else -> copy()
                }
            }
        }.first.run { first * second }.toString()
}

fun main() {
    println("Day 02")

    check(runPart1<Day02Solver>("day02-test").toInt() == 150)
    println("Part 1: ${runPart1<Day02Solver>("day02-input")}")

    check(runPart2<Day02Solver>("day02-test").toInt() == 900)
    println("Part 2: ${runPart2<Day02Solver>("day02-input")}")
}