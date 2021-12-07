import kotlin.math.*

class Day05Solver : TaskSolver {
    override fun solvePart1(input: List<String>): String = solve(input, false)

    override fun solvePart2(input: List<String>): String = solve(input, true)

    private fun solve(input: List<String>, diagonals: Boolean): String {
        val segments: List<Pair<Pair<Int, Int>, Pair<Int, Int>>> = input.map {
            val points = it.split(" -> ").map { p -> p.split(",").map(String::toInt) }
            Pair(Pair(points[0][0], points[0][1]), Pair(points[1][0], points[1][1]))
        }

        val maxX = segments.map { Pair(it.first.first, it.second.first).toList() }.flatten().maxOrNull() ?: 0
        val maxY = segments.map { Pair(it.first.second, it.second.second).toList() }.flatten().maxOrNull() ?: 0
        val grid = List(maxX + 1) { MutableList(maxY + 1) { 0 } }

        segments
            .filter { diagonals || it.first.first == it.second.first || it.first.second == it.second.second }
            .forEach {
                val delta = if (it.first.first != it.second.first) (it.first.first - it.second.first).absoluteValue
                            else (it.first.second - it.second.second).absoluteValue
                val factor = it.asVector().magnitude() / delta
                val gradient = it.asVector().gradient()
                for (i in 0..delta) {
                    grid[it.first.first + round(i * factor * cos(gradient)).toInt()][it.first.second + round(i * factor * sin(gradient)).toInt()] += 1
                }
            }

//        grid.transpose()
//            .forEach {
//                it
//                    .map(Int::toString)
//                    .map { s -> if (s == "0") "." else s }
//                    .forEach { a -> print(a) }
//                println()
//            }

        return grid.flatten().count { it > 1 }.toString()
    }
}

typealias Vector = Pair<Double, Double>
private fun Pair<Pair<Int, Int>, Pair<Int, Int>>.asVector(): Vector = Pair((second.first - first.first).toDouble(),
                                                                           (second.second - first.second).toDouble())
private fun Vector.magnitude(): Double = sqrt(first.pow(2) + second.pow(2))
private fun Vector.normalised(): Vector = Pair(first / magnitude(), second / magnitude())
private fun Vector.gradient(): Double = with(normalised()) { atan2(1.0, 0.0) - atan2(first, second) }

fun main() {
    println("Day 05")

    check(runPart1<Day05Solver>("day05-test").toInt() == 5)
    println("Part 1: ${runPart1<Day05Solver>("day05-input")}")

    check(runPart2<Day05Solver>("day05-test").toInt() == 12)
    println("Part 2: ${runPart2<Day05Solver>("day05-input")}")
}
